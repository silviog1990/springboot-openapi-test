package dev.silvio.springboot.openapi.services;

import dev.silvio.springboot.openapi.data.dynamodb.entities.BookEntity;
import dev.silvio.springboot.openapi.data.dynamodb.entities.BookID;
import dev.silvio.springboot.openapi.data.dynamodb.repositories.BookRepository;
import dev.silvio.springboot.openapi.models.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @Before
    public void setUp() throws Exception {
        bookService = new BookService(bookRepository);
    }

    @Test
    public void getBookDetails_returnBookInfo() {
        BookID id = new BookID("abc", "title");
        List<String> authors = new ArrayList<String>();
        authors.add("Silvio Gay");
        BDDMockito.given(bookRepository.findByIsbn("abc")).willReturn(Optional.of(new BookEntity(id, authors)));
        Book book = bookService.getBookByIsbn(id);
        assertEquals(book.getIsbn(), id.getIsbn());
        assertEquals(book.getName(), id.getName());
        assertIterableEquals(book.getAuthors(), authors);
    }

    @Test
    public void getAllBooksDetails_returnAllBooksInfo() {
        List<BookEntity> allBooksEntity = new ArrayList<>();
        BookID id = new BookID("abc", "title");
        List<String> authors = new ArrayList<String>();
        authors.add("Silvio Gay");
        allBooksEntity.add(new BookEntity(id, authors));

        id = new BookID("zxc", "title2");
        authors = new ArrayList<String>();
        authors.add("Douglas Adams");
        allBooksEntity.add(new BookEntity(id, authors));

        BDDMockito.given((List<BookEntity>) bookRepository.findAll()).willReturn(allBooksEntity);
        List<Book> books = bookService.getBooks();
        assertIterableEquals(books, Book.listBookEntityToListBook(allBooksEntity));
    }

}