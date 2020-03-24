package com.niko.restbooks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {
    @Query("select b from BookEntity b")
    List<BookEntity> getAllBooks();

    List<BookEntity> getAllByTitle(String title);

    BookEntity getBookEntityByBookNumber(int bookNumber);

    BookEntity getBookEntityByIsbn(String isbn);
}
