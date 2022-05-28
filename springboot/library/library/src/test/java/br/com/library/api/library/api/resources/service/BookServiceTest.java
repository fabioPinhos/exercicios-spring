package br.com.library.api.library.api.resources.service;


import br.com.library.api.library.repository.BookRepository;
import br.com.library.api.library.service.impl.BookServiceImpl;
import br.com.library.api.library.entity.Book;
import br.com.library.api.library.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {


    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setUp(){
        this.service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTest(){

        Book entity = Book.builder().id(10).autor("Autor").nome("Meu livro").isbn("isbn").build();

        Mockito.when(repository.save(entity)).thenReturn(
                Book.builder()
                        .id(11)
                        .titulo("Meu livro")
                        .autor("Autor").build()
        );

        Book save = service.save(entity);

        assertThat(save.getId()).isNotNull();

    }

}
