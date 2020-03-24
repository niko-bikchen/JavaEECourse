package com.niko.restbooks;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
