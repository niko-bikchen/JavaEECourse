package com.niko.restbooks.dto;

import com.niko.restbooks.BookEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class BooksResponseDto {
    private final List<BookEntity> books;
}
