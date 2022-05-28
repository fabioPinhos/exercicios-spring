package br.com.library.api.library.api.resources.model.repository;

import br.com.library.api.library.entity.Book;
import br.com.library.api.library.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um livro na base com isbn informado")
    public void returnTrueWhenIsbnExists(){

        //cenario
        String isbn = "123";

        Book entity = Book.builder().autor("Autor").nome("Meu livro").isbn("123").build();

        testEntityManager.persist(entity);

        //execucao
        boolean existsById = repository.existsByIsbn(isbn);

        //verificacao
        Assertions.assertThat(existsById).isTrue();
    }

    @Test
    @DisplayName("Deve retornar falso quando nao existir um livro na base com isbn informado")
    public void returnFalseWhenIsbnExists(){

        //cenario
        String isbn = "123";

        //execucao
        boolean existsById = repository.existsByIsbn(isbn);

        //verificacao
        Assertions.assertThat(existsById).isFalse();
    }

}
