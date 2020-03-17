package com.bikchen.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookListController {
    private final BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String bookList(Model model) {
        List<BookEntity> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "book/{id}")
    public String getBook(@PathVariable("id") Integer id, Model model) {
        BookEntity book = bookService.getBookByBookNumber(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(value = "filter-books", method = RequestMethod.POST)
    public String findBook(@RequestParam("filter-input") String filterInput,
                           @RequestParam("filter-criteria") String filterCriteria,
                           Model model) {
        if (filterInput.equals("")) {
            return "redirect:/";
        }

        switch (filterCriteria) {
            case "title": {
                List<BookEntity> books = bookService.getBooksByTitle(filterInput);
                model.addAttribute("books", books);
                break;
            }
            case "isbn": {
                BookEntity book = bookService.getBookByISBN(filterInput);

                List<BookEntity> books = new ArrayList<>();
                books.add(book);

                model.addAttribute("books", books);
                break;
            }
            case "":
                return "redirect:/";
        }

        return "index";
    }

    @RequestMapping(value = "add-book-form", method = RequestMethod.GET)
    public String addBookForm() {
        return "add-book-form";
    }

    @RequestMapping(value = "add-book", method = RequestMethod.POST)
    public String addBook(@ModelAttribute BookEntity book) {
        if (!book.getAuthor().isEmpty() && !book.getTitle().isEmpty() && !book.getIsbn().isEmpty()) {
            bookService.addNewBook(book);
        }

        return "redirect:/";
    }
}
