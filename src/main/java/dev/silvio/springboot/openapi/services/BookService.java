package dev.silvio.springboot.openapi.services;

import java.util.ArrayList;
import java.util.List;

import dev.silvio.springboot.openapi.data.dynamodb.entities.BookID;
import dev.silvio.springboot.openapi.exceptions.BookNotFoundException;
import dev.silvio.springboot.openapi.exceptions.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.silvio.springboot.openapi.data.dynamodb.entities.BookEntity;
import dev.silvio.springboot.openapi.data.dynamodb.repositories.BookRepository;
import dev.silvio.springboot.openapi.models.Book;

@Slf4j
@Service
public class BookService {

    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book getBookByIsbn(BookID id) {
        BookEntity entity = this.repository.findByIsbn(id.getIsbn()).orElseThrow(() -> new BookNotFoundException());
        Book book = Book.bookEntityToBook(entity);
        return book;
    }

    public List<Book> getBooks() {
        List<BookEntity> books = (List<BookEntity>) this.repository.findAll();
        if (books.size() == 0) throw new BookNotFoundException();
        List<Book> res = Book.listBookEntityToListBook(books);
        return res;
    }

}
