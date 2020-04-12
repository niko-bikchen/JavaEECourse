package com.niko.restbooks.services;

import com.niko.restbooks.entities.BookEntity;
import com.niko.restbooks.entities.UserEntity;
import com.niko.restbooks.exceptions.NotFoundException;
import com.niko.restbooks.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addNewUser(UserEntity user) {
        userRepository.saveAndFlush(user);
    }

    public List<BookEntity> getFavouriteBooks(String login) {
        UserEntity user = userRepository.findByLogin(login).orElseThrow(() -> new NotFoundException("No user for login: " + login));

        return user.getFavouriteBooks();
    }

    public List<BookEntity> addBookToFavourites(BookEntity book, String login) throws NotFoundException {
        UserEntity user = userRepository.findByLogin(login).orElseThrow(() -> new NotFoundException("No user for login: " + login));
        List<BookEntity> favouriteBooks = user.getFavouriteBooks();

        favouriteBooks.add(book);
        user.setFavouriteBooks(favouriteBooks);

        userRepository.saveAndFlush(user);

        return favouriteBooks;
    }

    public List<BookEntity> removeBookFromFavourites(Integer id, String login) throws NotFoundException {
        UserEntity user = userRepository.findByLogin(login).orElseThrow(() -> new NotFoundException("No user for login: " + login));
        List<BookEntity> favouriteBooks = user.getFavouriteBooks();

        favouriteBooks.removeIf((book) -> book.getBookNumber().equals(id));
        user.setFavouriteBooks(favouriteBooks);

        userRepository.saveAndFlush(user);

        return favouriteBooks;
    }

    public Optional<UserEntity> findByLogin(String login) throws NotFoundException {
        return userRepository.findByLogin(login);
    }

    public Optional<UserEntity> findByUserNumber(Integer userNumber) throws NotFoundException {
        return userRepository.findByUserNumber(userNumber);
    }
}
