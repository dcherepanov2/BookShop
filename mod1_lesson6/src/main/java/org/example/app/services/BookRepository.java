package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Consumer;


@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(context.getBean(IdProvider.class).provideId(book));
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(String bookIdToRemove) {
        for (Book book : retreiveAll()) {
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
        for (Book book : retreiveAll()) {
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
        for (Book book : retreiveAll()) {
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

    @Override
    public void sortById() {
        logger.info("repository sort by id is working...");
        repo.sort(Comparator.comparing(Book::getId).reversed());
        //сортировка через компаратор
        logger.info(repo.toString());
    }

    @Override
    public void sortByAuthor() {
        logger.info("repository sort by id is working...");
        repo.sort(Comparator.comparing(Book::getAuthor));//сортировка через компаратор
        logger.info(repo.toString());
    }

    @Override
    public void sortByTittle() {
        logger.info("repository sort by id is working...");
        repo.sort(Comparator.comparing(Book::getTitle));//сортировка через компаратор
        logger.info(repo.toString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
