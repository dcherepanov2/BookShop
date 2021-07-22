package org.example.services;


import org.apache.log4j.Logger;
import org.example.repo.ProjectRepository;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService {

    private final Logger logger = Logger.getLogger(BookRepository.class);


    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retrieveAll();
    }

    public boolean hasAllValuesEmpty(Book book){//функция проверки книги на пустые значения
        return book.getAuthor().equals("") || book.getTitle().equals("") || book.getSize() == null;
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean removeBookById(String bookIdToRemove) {
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public void removeBookByAuthor(String Author){
        logger.info("Service func with remove book to author work is normal");
        while (bookRepo.removeItemByAuthor(Author));
    }
    public void removeBookByTitle(String title){
        logger.info("Service func with remove book to author work is normal");
        while (bookRepo.removeItemByTitle(title));
    }

    public List<Book> findId(String id){
        logger.info("service find id is working...");
        return bookRepo.findID(id);
    }

    public List<Book> findTitle(String id){
        logger.info("service find id is working...");
        return bookRepo.findTitle(id);
    }

    public List<Book> findAuthor(String id){
        logger.info("service find id is working...");
        return bookRepo.findAuthor(id);
    }
}
