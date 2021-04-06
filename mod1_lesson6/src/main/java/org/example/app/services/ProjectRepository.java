package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T>{
    List<T> retreiveAll();

    void store(T book);

    boolean removeItemById(String bookIdToRemove);

    boolean removeItemByAuthor(String Author);

    boolean removeItemByTitle(String title);

    void sortById();

    void sortByAuthor();

    void sortByTittle();
}
