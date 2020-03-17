package com.bikchen.starter;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Column(name = "bookNumber")
    private Integer bookNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Id
    @Column(name = "isbn")
    private String isbn;
}
