package dev.silvio.springboot.openapi.data.dynamodb.repositories;

import dev.silvio.springboot.openapi.data.dynamodb.entities.BookEntity;
import dev.silvio.springboot.openapi.data.dynamodb.entities.BookID;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface BookRepository extends CrudRepository<BookEntity, BookID> {
    Optional<BookEntity> findByIsbn(String isbn);
}