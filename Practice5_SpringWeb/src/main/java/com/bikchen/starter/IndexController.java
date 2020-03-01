package com.bikchen.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String bookList(Model model) {
        List<BookEntity> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "book/{id}")
    public String getBook(@PathVariable("id") Integer id, Model model) {
        BookEntity book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(value = "filter-books", method = RequestMethod.POST)
    public String findBook(@RequestParam("book-title") String bookTitle, Model model) {
        if (bookTitle.equals("")) {
            return "redirect:/";
        }

        List<BookEntity> books = bookService.getBooksByTitle(bookTitle);
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "add-book-form", method = RequestMethod.GET)
    public String addBookForm() {
        return "add-book-form";
    }

    @RequestMapping(value = "add-book", method = RequestMethod.POST)
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("author") String author,
                          @RequestParam("isbn") String isbn) {
        bookService.addNewBook(isbn, title, author);
        return "redirect:/";
    }
}
