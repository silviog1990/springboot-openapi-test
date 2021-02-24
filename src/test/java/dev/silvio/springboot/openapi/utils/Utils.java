package dev.silvio.springboot.openapi.utils;

import dev.silvio.springboot.openapi.data.dynamodb.entities.BookEntity;
import dev.silvio.springboot.openapi.data.dynamodb.entities.BookID;
import dev.silvio.springboot.openapi.models.Book;

import java.util.Arrays;

public class Utils {
    public static Book buildBook(String isbn, String name, String[] authors) {
        Book b = Book.builder()
                .isbn(isbn)
                .name(name)
                .authors(Arrays.asList(authors))
                .build();
        return b;
    }

    public static BookEntity buildBookEntity(String isbn, String name, String[] authors) {
        BookID id = new BookID(isbn, name);
        BookEntity b = BookEntity.builder()
                .id(id)
                .authors(Arrays.asList(authors))
                .build();
        return b;
    }
}
