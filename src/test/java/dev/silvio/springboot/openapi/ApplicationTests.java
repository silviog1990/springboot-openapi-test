package dev.silvio.springboot.openapi;

//import dev.silvio.springboot.openapi.models.Book;
//import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;

//import static org.junit.jupiter.api.Assertions.*;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;

//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest()
class ApplicationTests {

//    @Autowired
//    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }


    // integrated test with real api call
//	{
//		"ISBN": "asd",
//			"Name": "dune",
//			"Authors": [
//		"Frank Herbert"
//  ]
//	}
//	@Test
//	public void getBook_returnsBookDetails() throws Exception {
//		ResponseEntity<Book> response = restTemplate.getForEntity("/api/v1/book/asd",
//				Book.class);
//		// assert
//		log.info(response.getBody().toString());
//		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
//		assertThat(response.getBody().getIsbn(), is (equalTo("asd")));
//		assertThat(response.getBody().getName(), is (equalTo("dune")));
//	}

}
