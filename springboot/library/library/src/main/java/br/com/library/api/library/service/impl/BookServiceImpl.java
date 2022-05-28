package br.com.library.api.library.service.impl;

import br.com.library.api.library.entity.Book;
import br.com.library.api.library.repository.BookRepository;
import br.com.library.api.library.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
       return repository.save(book);
    }

}
