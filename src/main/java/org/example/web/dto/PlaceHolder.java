package org.example.web.dto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class PlaceHolder {
    @Getter
    @Setter
    String string;

    public PlaceHolder(String string) {
        this.string = string;
    }
}
