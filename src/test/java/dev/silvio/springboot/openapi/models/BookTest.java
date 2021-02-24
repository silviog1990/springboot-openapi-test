package dev.silvio.springboot.openapi.models;

import dev.silvio.springboot.openapi.data.dynamodb.entities.BookEntity;
import dev.silvio.springboot.openapi.data.dynamodb.entities.BookID;
import dev.silvio.springboot.openapi.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void test_bookEntityToBook() {
        Book b = Utils.buildBook("isbn1", "title1", new String[]{"Silvio"});
        BookEntity be = Utils.buildBookEntity("isbn1", "title1", new String[]{"Silvio"});
        assertEquals(b, Book.bookEntityToBook(be));
    }

    @Test
    public void test_listBooksEntityToListBooks() {
        Book b1 = Utils.buildBook("isbn1", "title1", new String[]{"Silvio1"});
        BookEntity be1 = Utils.buildBookEntity("isbn1", "title1", new String[]{"Silvio1"});
        Book b2 = Utils.buildBook("isbn2", "title2", new String[]{"Silvio2"});
        BookEntity be2 = Utils.buildBookEntity("isbn2", "title2", new String[]{"Silvio2"});
        List<Book> lb = new ArrayList<>();
        lb.add(b1);
        lb.add(b2);
        List<BookEntity> lbe = new ArrayList<>();
        lbe.add(be1);
        lbe.add(be2);
        assertIterableEquals(lb, Book.listBookEntityToListBook(lbe));
    }

}