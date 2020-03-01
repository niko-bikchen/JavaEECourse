package com.bikchen.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final EntityManager entityManager;
    private Integer entitiesId = 0;

    @Transactional
    public void addNewBook(String isbn, String title, String author) {
        BookEntity book = new BookEntity();

        book.setBNum(entitiesId++);
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);

        entityManager.merge(book);
    }

    @Transactional
    public BookEntity getBookById(int bookNum) {
        return entityManager
                .createQuery("select b from BookEntity b where b.bNum = :bookNum", BookEntity.class)
                .setParameter("bookNum", bookNum)
                .getSingleResult();
    }

    @Transactional
    public List<BookEntity> getBooksByTitle(String bookTitle) {
        return entityManager
                .createQuery("select b from BookEntity b where b.title = :bookTitle", BookEntity.class)
                .setParameter("bookTitle", bookTitle)
                .getResultList();
    }

    @Transactional
    public List<BookEntity> getAllBooks() {
        return entityManager.createQuery("select b from BookEntity b").getResultList();
    }
}