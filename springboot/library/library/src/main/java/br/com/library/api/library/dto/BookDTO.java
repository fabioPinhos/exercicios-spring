package br.com.library.api.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDTO {

    private int id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String autor;

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String isbn;
}
