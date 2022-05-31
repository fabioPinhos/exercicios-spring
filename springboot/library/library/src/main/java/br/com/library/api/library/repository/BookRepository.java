package br.com.library.api.library.repository;

import br.com.library.api.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Boolean existsByIsbn(String isbn);
}
