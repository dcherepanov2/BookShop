package org.example.app.repo;


import org.apache.log4j.Logger;
import org.example.app.services.IdProvider;
import org.example.web.dto.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository

public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);

    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;//используется в 26 строчке

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    public BookRepository() {
    }

    @Override
    public void store(Book book) {
        book.setId(context.getBean(IdProvider.class).provideId(book));
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(String bookIdToRemove) {
        for (Book book : retrieveAll()) {
            logger.info("find book...");
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            } else {
                logger.info("books not find");
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByAuthor(String Author) {
        for (Book book : retrieveAll()) {
            logger.info("find author...");
            logger.info(Author);
            if (book.getAuthor().equals(Author)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            } else {
                logger.info("books not find");
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByTitle(String title) {
        for (Book book : retrieveAll()) {
            logger.info("find author...");
            logger.info(title);
            if (book.getTitle().equals(title)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            } else {
                logger.info("books not find");
            }
        }
        return false;
    }

    //найти книги по id
    @Override
    public List<Book> findID(String id) {
        List<Book> list = new ArrayList<>();
        for(Book book : retrieveAll()){
            if(book.getId().contains(id)){
                list.add(book);
            }
        }
        return list;
    }

    @Override
    public List<Book> findTitle(String id) {
        List<Book> list = new ArrayList<>();
        for(Book book : retrieveAll()){
            if(book.getTitle().contains(id)){
                list.add(book);
            }
        }
        return list;
    }

    @Override
    public List<Book> findAuthor(String id) {
        List<Book> list = new ArrayList<>();
        for(Book book : retrieveAll()){
            if(book.getAuthor().contains(id)){
                list.add(book);
            }
        }
        return list;
    }
}
