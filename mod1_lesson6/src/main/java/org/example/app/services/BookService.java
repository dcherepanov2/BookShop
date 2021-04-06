package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BookService {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final ProjectRepository<Book> bookRepo;
    private int flag=0;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

    public static boolean hasAllValuesEmpty(Book book){//функция проверки книги на пустые значения
        if(book.getAuthor().equals("")&&book.getTitle().equals("")&&book.getSize()==null){
            return true;
        }
        return false;
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookById(String bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public void removeBookByAuthor(String Author){
        logger.info("Service func with remove book to author work is normal");
        while (bookRepo.removeItemByAuthor(Author)){
            flag++;
        }
    }
    public void removeBookByTitle(String title){
        logger.info("Service func with remove book to author work is normal");
        while (bookRepo.removeItemByTitle(title)){
            flag++;
        }
    }
    public void sortById(){
        logger.info("service sort by id is working...");
        bookRepo.sortById();
    }
    public void sortByAuthor(){
        logger.info("service sort by id is working...");
        bookRepo.sortByAuthor();
    }
    public void sortByTittle(){
        logger.info("service sort by id is working...");
        bookRepo.sortByTittle();
    }
}
