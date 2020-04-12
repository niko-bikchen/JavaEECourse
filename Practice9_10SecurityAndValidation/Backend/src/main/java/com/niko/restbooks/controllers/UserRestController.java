package com.niko.restbooks.controllers;

import com.niko.restbooks.dto.BooksResponseDto;
import com.niko.restbooks.entities.BookEntity;
import com.niko.restbooks.exceptions.NotFoundException;
import com.niko.restbooks.helpers.RequestHelper;
import com.niko.restbooks.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserRestController {
    private final UserService userService;

    private final RequestHelper requestHelper;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @GetMapping("/fav-books")
    public ResponseEntity<BooksResponseDto> getAllFavouriteBooks(HttpServletRequest request) throws NotFoundException {
        String login = userService.
                findByUserNumber(requestHelper.getCurrentUser(request).
                        getUserNumber())
                .orElseThrow(new NotFoundException("No books for this user"))
                .getLogin();


        return ResponseEntity.status(HttpStatus.OK).body(BooksResponseDto.of(userService.getFavouriteBooks(login)));
    }

    @ResponseBody
    @PostMapping("/fav-books")
    public ResponseEntity<BooksResponseDto> addBookToFavourites(@Valid @RequestBody BookEntity book, HttpServletRequest request) throws NotFoundException {
        String login = userService.
                findByUserNumber(requestHelper.getCurrentUser(request).
                        getUserNumber())
                .orElseThrow(new NotFoundException("Cannot add book to favourites since current user does not exist"))
                .getLogin();

        userService.addBookToFavourites(book, login);

        return ResponseEntity.status(HttpStatus.OK).body(BooksResponseDto.of(userService.getFavouriteBooks(login)));
    }

    @DeleteMapping("/fav-books/{bookNumber}")
    public ResponseEntity<BooksResponseDto> removeBookFromFavourites(@PathVariable Integer bookNumber, HttpServletRequest request) throws NotFoundException {
        String login = userService.
                findByUserNumber(requestHelper.getCurrentUser(request).
                        getUserNumber())
                .orElseThrow(new NotFoundException("Cannot remove book from favourites since current user does not exist"))
                .getLogin();

        userService.removeBookFromFavourites(bookNumber, login);

        return ResponseEntity.status(HttpStatus.OK).body(BooksResponseDto.of(userService.getFavouriteBooks(login)));
    }
}
