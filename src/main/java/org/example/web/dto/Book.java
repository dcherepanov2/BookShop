package org.example.web.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class Book {

    private String id;
    private String author;
    private String title;
    private Integer size;
}
