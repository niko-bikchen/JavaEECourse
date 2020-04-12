package com.niko.restbooks.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class SearchRequestDto {
    @NotNull(message = "Search criteria cannot be empty")
    @Size(min = 1, max = 100)
    private final String searchCriteria;

    @NotNull(message = "Search input cannot be empty")
    @Size(min = 1, max = 100)
    private final String searchInput;
}
