package dev.silvio.springboot.openapi.controllers;

import dev.silvio.springboot.openapi.data.dynamodb.entities.BookID;
import dev.silvio.springboot.openapi.models.Book;
import dev.silvio.springboot.openapi.services.BookService;
import dev.silvio.springboot.openapi.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookApiController.class)
public class BookApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private BookApiController bookApiController;

    @Before
    public void setUp() throws Exception {
        bookApiController = new BookApiController(bookService);
    }

    @Test
    public void getBookByIsbn_ShouldReturnBook() throws Exception {
        String isbn = "isbn1";
        Book b = Utils.buildBook(isbn, "title1", new String[]{"Silvio"});

        // need to define equals and hashcode into bookid class
        BDDMockito.given(bookService.getBookByIsbn(BookID.builder().isbn(isbn).build())).willReturn(b);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/" + isbn))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("isbn").value(isbn))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("title1"))
                .andExpect(MockMvcResultMatchers.jsonPath("authors").value(Matchers.containsInAnyOrder("Silvio")));
    }

}