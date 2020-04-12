package com.niko.restbooks.controllers;

import com.niko.restbooks.entities.BookEntity;
import com.niko.restbooks.exceptions.BadRequestException;
import com.niko.restbooks.services.BookService;
import com.niko.restbooks.dto.SearchRequestDto;
import com.niko.restbooks.dto.BooksResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookListRestController {
    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<BooksResponseDto> bookList() {
        return ResponseEntity.status(HttpStatus.OK).body(BooksResponseDto.of(bookService.getAllBooks()));
    }

    @PostMapping("")
    public ResponseEntity<BooksResponseDto> addBook(@Valid @RequestBody BookEntity book) {
        bookService.addNewBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(BooksResponseDto.of(bookService.getAllBooks()));
    }

    @PostMapping("/search")
    public ResponseEntity<BooksResponseDto> findBook(@Valid @RequestBody SearchRequestDto dto) throws BadRequestException {
        String filterInput = dto.getSearchInput();
        String filterCriteria = dto.getSearchCriteria();
        List<BookEntity> books;

        switch (filterCriteria) {
            case "title": {
                books = bookService.getBooksByTitle(filterInput);

                break;
            }
            case "isbn": {
                BookEntity book = bookService.getBookByISBN(filterInput);

                books = new ArrayList<>();
                books.add(book);

                break;
            }
            default:
                throw new BadRequestException("Invalid filter criteria");
        }

        return ResponseEntity.status(HttpStatus.OK).body(BooksResponseDto.of(books));
    }
}
