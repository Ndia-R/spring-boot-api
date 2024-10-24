package com.example.spring_boot_api.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.spring_boot_api.dto.BookDto;
import com.example.spring_boot_api.dto.BookResponseDto;
import com.example.spring_boot_api.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDto> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/search")
    public BookResponseDto searchByTitle(@RequestParam String q, @PageableDefault(page = 0,
            size = 20, sort = "title", direction = Direction.ASC) Pageable pageable) {
        return bookService.searchByTitle(q, pageable);
    }

    @GetMapping("/discover")
    public BookResponseDto searchByGenreId(@RequestParam String genreIds, @PageableDefault(page = 0,
            size = 20, sort = "title", direction = Direction.ASC) Pageable pageable) {
        List<String> ids = Arrays.asList(genreIds.split(","));
        return bookService.searchByGenreId(ids, pageable);
    }

}
