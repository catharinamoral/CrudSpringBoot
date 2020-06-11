package com.accenture.demobookapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Long createNewBook(BookRequest bookRequest) {

        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());

        book = bookRepository.save(book);

        return book.getId();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {

        Optional<Book> requestedBook =  bookRepository.findById(id);

        if(requestedBook.isEmpty()){
            throw new BookNotFoundException((String.format("Book with id: '%s' not found")));
        }

        return requestedBook.get();

    }

    @Transactional
    public Book updateBook(Long id, BookRequest bookToUpdateRequest) {

        Optional<Book> bookFromDataBase = bookRepository.findById(id);

        if(bookFromDataBase.isEmpty()){
            throw new BookNotFoundException((String.format("Book with id: '%s' not found")));
        }

        Book bookToUpdate = bookFromDataBase.get();

        bookToUpdate.setAuthor(bookToUpdateRequest.getAuthor());
        bookToUpdate.setIsbn(bookToUpdateRequest.getIsbn());
        bookToUpdate.setTitle(bookToUpdateRequest.getTitle());

        return bookToUpdate;
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
