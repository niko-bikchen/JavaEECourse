package com.niko.restbooks.services;

import com.niko.restbooks.entities.BookEntity;
import com.niko.restbooks.exceptions.NotFoundException;
import com.niko.restbooks.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public void addNewBook(BookEntity book) {
        bookRepository.saveAndFlush(book);
    }

    @Transactional
    public BookEntity getBookByBookNumber(int bookNumber) throws NotFoundException {
        return bookRepository.getBookEntityByBookNumber(bookNumber).orElseThrow(() -> new NotFoundException("No book numbered: " + bookNumber));
    }

    @Transactional
    public List<BookEntity> getBooksByTitle(String bookTitle) {
        return bookRepository.getAllByTitle(bookTitle);
    }

    @Transactional
    public BookEntity getBookByISBN(String isbn) throws NotFoundException {
        return bookRepository.getBookEntityByIsbn(isbn).orElseThrow(() -> new NotFoundException("No book with ISBN: " + isbn));
    }

    @Transactional
    public List<BookEntity> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}