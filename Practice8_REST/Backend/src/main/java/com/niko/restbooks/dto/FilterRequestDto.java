package com.niko.restbooks.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class FilterRequestDto {
    private final String filterCriteria;
    private final String filterInput;
}
