package com.bikchen.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private Integer entitiesId = 0;

    @Transactional
    public void addNewBook(BookEntity book) {
        book.setBookNumber(entitiesId++);

        bookRepository.saveAndFlush(book);
    }

    @Transactional
    public BookEntity getBookByBookNumber(int bookNumber) {
        return bookRepository.getBookEntityByBookNumber(bookNumber);
    }

    @Transactional
    public List<BookEntity> getBooksByTitle(String bookTitle) {
        return bookRepository.getAllByTitle(bookTitle);
    }

    @Transactional
    public BookEntity getBookByISBN(String isbn) {
        return bookRepository.getBookEntityByIsbn(isbn);
    }

    @Transactional
    public List<BookEntity> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}