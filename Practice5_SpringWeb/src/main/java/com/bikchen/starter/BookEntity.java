package com.bikchen.starter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookEntity {
    @Column(name = "bNum")
    private Integer bNum;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Id
    @Column(name = "isbn")
    private String isbn;
}
