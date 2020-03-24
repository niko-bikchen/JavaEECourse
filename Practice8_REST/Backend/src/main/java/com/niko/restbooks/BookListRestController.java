package com.niko.restbooks;

import com.niko.restbooks.dto.FilterRequestDto;
import com.niko.restbooks.dto.BooksResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookListRestController {
    private final BookService bookService;

    @ResponseBody
    @GetMapping("books")
    public ResponseEntity<BooksResponseDto> bookList() {
        return ResponseEntity.status(HttpStatus.OK).body(BooksResponseDto.of(bookService.getAllBooks()));
    }

    @ResponseBody
    @PostMapping("filter-books")
    public ResponseEntity<BooksResponseDto> findBook(@RequestBody FilterRequestDto dto) {
        String filterInput = dto.getFilterInput();
        String filterCriteria = dto.getFilterCriteria();
        List<BookEntity> books;

        if (filterInput.equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BooksResponseDto.of(null));
        }


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
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BooksResponseDto.of(null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(BooksResponseDto.of(books));
    }

    @ResponseBody
    @PostMapping("add-book")
    public ResponseEntity<BooksResponseDto> addBook(@RequestBody BookEntity book) {
        if (!book.getAuthor().isEmpty() && !book.getTitle().isEmpty() && !book.getIsbn().isEmpty()) {
            bookService.addNewBook(book);
            return ResponseEntity.status(HttpStatus.OK).body(BooksResponseDto.of(bookService.getAllBooks()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BooksResponseDto.of(null));
        }
    }
}
