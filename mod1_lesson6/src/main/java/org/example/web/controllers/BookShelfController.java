package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookAuthorToRemove",new String());
        model.addAttribute("bookTittleToRemove",new String());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        if (BookService.hasAllValuesEmpty(book)) {
            logger.info("book is null ; error add book ; redirect to /books/shelf");
            return "redirect:/books/shelf";
        }
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") String bookIdToRemove) {
        bookService.removeBookById(bookIdToRemove);
        return "redirect:/books/shelf";
    }

     @PostMapping("/removeToAuthor")//удаление по автору
    public String removeBookToAuthor(@RequestParam(required=false,value = "bookAuthorToRemove")String author){
        logger.info("remove to author start work");
        logger.info(author);
        bookService.removeBookByAuthor(author);
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeToTittle")//удаление по тайтлу
    public String removeBookToTitle(@RequestParam(required=false,value = "bookTittleToRemove")String title){
        logger.info("remove to title start work");
        logger.info(title);
        bookService.removeBookByTitle(title);
        return "redirect:/books/shelf";
    }

    @PostMapping("/filterToId")//фильтрация по id
    public String sortById() {
        logger.info("method sort by id is working...");
        bookService.sortById();
        return "redirect:/books/shelf";
    }

    @PostMapping("/filterToAuthor")//фильтрация по автору
    public String sortByAuthor() {
        logger.info("method sort by author is working...");
        bookService.sortByAuthor();
        return "redirect:/books/shelf";
    }

    @PostMapping("/filterToTittle")//фильтрация по тайтлу
    public String sortByTittle() {
        logger.info("method sort by title is working...");
        bookService.sortByTittle();
        return "redirect:/books/shelf";
    }


}
