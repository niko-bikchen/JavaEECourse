package com.niko.restbooks.repositories;

import com.niko.restbooks.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Query("select b from BookEntity b")
    List<BookEntity> getAllBooks();

    List<BookEntity> getAllByTitle(String title);

    Optional<BookEntity> getBookEntityByBookNumber(int bookNumber);

    Optional<BookEntity> getBookEntityByIsbn(String isbn);
}
