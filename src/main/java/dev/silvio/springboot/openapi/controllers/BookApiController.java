package dev.silvio.springboot.openapi.controllers;

import java.util.List;

import dev.silvio.springboot.openapi.api.BookApiV1;
import dev.silvio.springboot.openapi.data.dynamodb.entities.BookID;
import dev.silvio.springboot.openapi.exceptions.BookNotFoundException;
import dev.silvio.springboot.openapi.exceptions.InternalServerErrorException;
import dev.silvio.springboot.openapi.models.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.silvio.springboot.openapi.models.Book;
import dev.silvio.springboot.openapi.services.BookService;

@Slf4j
@RestController
public class BookApiController implements BookApiV1 {

    private BookService service;

    @Autowired
    public BookApiController(BookService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Book> getBookByIsbn(String isbn) {
        Book book = this.service.getBookByIsbn(BookID.builder().isbn(isbn).build());
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = this.service.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Override
    public void bookNotFoundHandler(BookNotFoundException ex) {
    }

    @Override
    public ResponseEntity<Error> internalServerErrorHandler(InternalServerErrorException ex) {
        return new ResponseEntity<Error>(new Error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
