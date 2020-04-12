package com.niko.restbooks.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookNumber")
    private Integer bookNumber;

    @Column(name = "title")
    @NotNull(message = "Title cannot be empty")
    @Size(min = 1, max = 200)
    private String title;

    @Column(name = "author")
    @NotNull(message = "Author cannot be empty")
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[a-zA-Z.']+ [a-zA-Z.']+( [a-zA-Z.']+)?( [a-zA-Z.']+)?$",
            message = "Author name should consist of two to four words." +
                    "Words can contain letters, dots and apostrophes")
    private String author;

    @Column(name = "isbn", unique = true)
    @NotNull(message = "ISBN cannot be empty")
    @Pattern(regexp = "^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$", message = "ISBN should consist of 13 digits")
    private String isbn;
}
