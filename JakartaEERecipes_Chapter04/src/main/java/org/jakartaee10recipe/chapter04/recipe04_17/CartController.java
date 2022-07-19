package org.jakartaee10recipe.chapter04.recipe04_17;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIOutput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jakartaee10recipe.chapter04.recipe04_09.AuthorController;

import java.io.Serializable;

@SessionScoped
@Named("ch3CartController")
public class CartController implements Serializable {
    private Cart cart = null;
    private Item currentBook = null;

    @Inject
    private AuthorController authorController;

    /**
     * Creates a new instance of CartController
     */
    public CartController() {
    }

    public String addToCart() {
        if (getCart() == null) {
            cart = new Cart();
            getCart().addBook(getAuthorController().getCurrentBook(), 1);
        } else {
            System.out.println("adding book to cart...");
            getCart().addBook(getAuthorController().getCurrentBook(),
                    searchCart(getAuthorController().getCurrentBook().getTitle()) + 1);
        }
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succesfully Updated Cart", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return null;
    }

    /**
     * Determines if a book is already in the shopping cart
     *
     * @param title
     * @return
     */
    public int searchCart(String title) {
        int count = 0;

        for (Item item : getCart().getBooks()) {
            if (item.getBook().getTitle().equals(title)) {
                count++;
            }
        }
        return count;
    }

    public String viewCart() {
        if (cart == null) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No books in cart...", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

        return "/chapter05/cart";
    }

    public String continueShopping() {
        return "/chapter05/book";
    }

    public String editItem(String title) {
        for (Item item : cart.getBooks()) {
            if (item.getBook().getTitle().equals(title)) {
                currentBook = item;
            }
        }
        return "/chapter05/reviewItem";

    }

    public String updateCart(String title) {
        Item foundItem = null;
        if (currentBook.getQuantity() == 0) {
            for (Item item : cart.getBooks()) {
                if (item.getBook().getTitle().equals(title)) {
                    foundItem = item;
                }
            }
            cart.getBooks().remove(foundItem);
        }

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succesfully Updated Cart", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return "/chapter05/cart";
    }

    /**
     * @return the cart
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * @return the currentBook
     */
    public Item getCurrentBook() {
        return currentBook;
    }

    /**
     * @param currentBook the currentBook to set
     */
    public void setCurrentBook(Item currentBook) {
        this.currentBook = currentBook;
    }

    public void isBookInCart(ComponentSystemEvent event) {
        UIOutput output = (UIOutput) event.getComponent();
        if (cart != null) {
            if (searchCart(getAuthorController().getCurrentBook().getTitle()) > 0) {
                output.setValue("This book is currently in your cart.");
            } else {
                output.setValue("This book is not in your cart.");
            }
        } else {
            output.setValue("This book is not in your cart.");
        }
    }


    /**
     * @return the authorController
     */
    public AuthorController getAuthorController() {
        return authorController;
    }

    /**
     * @param authorController the authorController to set
     */
    public void setAuthorController(AuthorController authorController) {
        this.authorController = authorController;
    }
}
