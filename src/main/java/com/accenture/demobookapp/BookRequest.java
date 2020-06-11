package com.accenture.demobookapp;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class BookRequest {

    //essas annotations servem para fazer o teste
    @NotEmpty
    private String title;

    @NotEmpty
    @Size(max = 20)
    private String isbn;

    @NotEmpty
    private String author;


}
