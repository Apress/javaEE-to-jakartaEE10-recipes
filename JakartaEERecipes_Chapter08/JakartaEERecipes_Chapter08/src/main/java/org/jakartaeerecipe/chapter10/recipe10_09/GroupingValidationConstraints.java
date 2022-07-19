package org.jakartaeerecipe.chapter10.recipe10_09;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.jakartaeerecipe.annotation.group.BookGroup;
import org.jakartaeerecipe.entity.Book;

import java.math.BigDecimal;
import java.util.Set;

public class GroupingValidationConstraints {

    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Book book = new Book();
        book.setId(BigDecimal.ONE);
        book.setTitle("The Best Java Book");

        Set<ConstraintViolation<Book>> violations = validator.validate(book, BookGroup.class);

        for (ConstraintViolation<Book> violation : violations) {
            System.out.println(violation.getMessage());
        }

    }
}
