package org.example.repo;

import org.example.web.dto.Book;

import java.util.List;


public interface ProjectRepository<T>{
    List<T> retrieveAll();

    void store(T book);

    boolean removeItemById(String bookIdToRemove);

    boolean removeItemByAuthor(String Author);

    boolean removeItemByTitle(String title);

    List<Book> findID(String id);

    List<Book> findTitle(String id);

    List<Book> findAuthor(String id);
}
