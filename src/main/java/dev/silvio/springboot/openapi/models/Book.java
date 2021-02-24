package dev.silvio.springboot.openapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.silvio.springboot.openapi.data.dynamodb.entities.BookEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Book {
    @Schema(description = "")
    @JsonProperty("isbn")
    private String isbn = null;

    @Schema(description = "")
    @JsonProperty("name")
    private String name = null;

    @Schema(description = "")
    @JsonProperty("authors")
    private List<String> authors = null;

    public static Book bookEntityToBook(BookEntity be) {
        return Book.builder()
                .isbn(be.getIsbn())
                .name(be.getName())
                .authors(be.getAuthors())
                .build();
    }

    public static List<Book> listBookEntityToListBook(List<BookEntity> lbe) {
        List<Book> lb = new ArrayList<>();
        lbe.forEach(be -> {
            lb.add(Book.bookEntityToBook(be));
        });
        return lb;
    }
}
