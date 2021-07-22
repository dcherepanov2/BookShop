package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.services.BookService;
import org.example.web.dto.Book;
import org.example.web.dto.PlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private final Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;
    private List<Book> findList = new ArrayList<>();

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("id", new PlaceHolder());
        model.addAttribute("findList", findList);
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        findList.clear();
        if (bookService.hasAllValuesEmpty(book)) {
            logger.info("book is null ; error add book ; redirect to /books/shelf");
            return "redirect:/books/shelf";
        }
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") String bookIdToRemove) {
        findList.clear();
        bookService.removeBookById(bookIdToRemove);
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeToAuthor")//удаление по автору
    public String removeBookToAuthor(@RequestParam(required = false, value = "bookAuthorToRemove") String author) {
        findList.clear();
        logger.info("remove to author start work");
        logger.info(author);
        bookService.removeBookByAuthor(author);
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeToTittle")//удаление по тайтлу
    public String removeBookToTitle(@RequestParam(required = false, value = "bookTittleToRemove") String title) {
        findList.clear();
        logger.info("remove to title start work");
        logger.info(title);
        bookService.removeBookByTitle(title);
        return "redirect:/books/shelf";
    }

    @PostMapping("/findId")
    public String findId(PlaceHolder id){
        findList.clear();
        logger.info("method find id is start working" + id.getString());
        if (id.getString() == null || id.getString().equals("")){
            logger.info("id == null");
            return "redirect:/books/shelf";//если передается пустое значение - возвращаются все книги в базе
        }
        findList = bookService.findId(id.getString());
        logger.info(findList);
        logger.info(bookService.findId(id.getString()));
        return "redirect:/books/shelf";
    }

    @PostMapping("/findTitle")
    public String findTitle(PlaceHolder id){
        findList.clear();
        logger.info("method find id is start working" + id.getString());
        if (id.getString() == null || id.getString().equals("")){
            logger.info("title == null");
            return "redirect:/books/shelf";//если передается пустое значение - возвращаются все книги в базе
        }
        findList = bookService.findTitle(id.getString());
        logger.info(findList);
        logger.info(bookService.findTitle(id.getString()));
        return "redirect:/books/shelf";
    }


    @PostMapping("/findAuthor")
    public String findAuthor(PlaceHolder id){
        findList.clear();
        logger.info("method find id is start working" + id.getString());
        if (id.getString() == null || id.getString().equals("")){
            logger.info("id == null");
            return "redirect:/books/shelf";//если передается пустое значение - возвращаются все книги в базе
        }
        findList = bookService.findAuthor(id.getString());
        logger.info(findList);
        logger.info(bookService.findAuthor(id.getString()));
        return "redirect:/books/shelf";
    }

}
