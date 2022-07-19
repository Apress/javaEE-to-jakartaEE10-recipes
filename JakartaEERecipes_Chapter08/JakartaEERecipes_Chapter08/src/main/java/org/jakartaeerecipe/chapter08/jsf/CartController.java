package org.jakartaeerecipe.chapter08.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIOutput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jakartaeerecipe.chapter08.object.Cart;
import org.jakartaeerecipe.chapter08.object.Item;
import org.jakartaeerecipe.chapter08.session.OrderFacade;

import java.io.Serializable;

@Named(value="cartController")
@SessionScoped
public class CartController implements Serializable {

    private Item currentBook = null;

    @EJB
    OrderFacade orderFacade;

    @Inject
    private AuthorController authorController;

    /**
     * Creates a new instance of CartController
     */
    public CartController() {
    }

    public String addToCart() {
        if (getCart().getBooks() == null) {
            getCart().addBook(authorController.getCurrentBook(), 1);
        } else {
            getCart().addBook(authorController.getCurrentBook(),
                    searchCart(authorController.getCurrentBook().getTitle()) + 1);
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
        if (getCart() == null) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No books in cart...", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

        return "/chapter08/cart";
    }

    public String continueShopping() {
        return "/chapter08/book.xhtml";
    }

    public String editItem(String title) {
        for (Item item : getCart().getBooks()) {
            if (item.getBook().getTitle().equals(title)) {
                currentBook = item;
            }
        }
        return "/chapter08/reviewItem";

    }

    public String updateCart(String title) {
        Item foundItem = null;
        if (currentBook.getQuantity() == 0) {
            for (Item item : getCart().getBooks()) {
                if (item.getBook().getTitle().equals(title)) {
                    foundItem = item;
                }
            }
        }
        getCart().getBooks().remove(foundItem);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Succesfully Updated Cart", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return "/chapter08/cart";
    }

    /**
     * @return the cart
     */
    public Cart getCart() {
        return orderFacade.getCart();
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
        if (getCart() != null) {
            if (searchCart(authorController.getCurrentBook().getTitle()) > 0) {
                output.setValue("This book is currently in your cart.");
            } else {
                output.setValue("This book is not in your cart.");
            }
        } else {
            output.setValue("This book is not in your cart.");
        }
    }
    /**
     public void updateRowData(RowEditEvent e) {
     System.out.println("Perform editing logic here...");
     currentBook = (Item)e.getObject();
     // Call the updateCart method, passing the title of the current book.
     updateCart(((Item)e.getObject()).getBook().getTitle());
     }
     */


}
