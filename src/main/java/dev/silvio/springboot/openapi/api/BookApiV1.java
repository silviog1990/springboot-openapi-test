package dev.silvio.springboot.openapi.api;

import java.util.List;

import dev.silvio.springboot.openapi.exceptions.BookNotFoundException;
import dev.silvio.springboot.openapi.exceptions.InternalServerErrorException;
import dev.silvio.springboot.openapi.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.silvio.springboot.openapi.models.Book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@RequestMapping("/api/v1/books")
public interface BookApiV1 {

    @Operation(summary = "Get book from dynamodb table Books by ISBN", description = "Get book from dynamodb table Books by ISBN", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "book not found by ISBN"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @RequestMapping(value = "/{ISBN}", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<Book> getBookByIsbn(
            @Parameter(in = ParameterIn.PATH,
                    description = "ISBN of getBookByIsbn",
                    required = true,
                    schema = @Schema()) @PathVariable("ISBN") String isbn);

    @Operation(summary = "Get books from dynamodb table Books", description = "Get books from dynamodb table Books", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))
            ),
            @ApiResponse(responseCode = "404", description = "books not founds"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @RequestMapping(value = "/", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<Book>> getBooks();

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void bookNotFoundHandler(BookNotFoundException ex);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<Error> internalServerErrorHandler(InternalServerErrorException ex);
}