package com.example.spring_boot_api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.spring_boot_api.dto.BookDto;
import com.example.spring_boot_api.dto.BookResponseDto;
import com.example.spring_boot_api.exception.NotFoundException;
import com.example.spring_boot_api.mapper.BookMapper;
import com.example.spring_boot_api.model.Book;
import com.example.spring_boot_api.repository.BookRepository;
import com.example.spring_boot_api.repository.BookRepositoryCustom;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRepositoryCustom bookRepositoryCustom;

    @Autowired
    private BookMapper bookMapper;

    private static final Integer DEFAULT_START_INDEX = 0;
    private static final Integer DEFAULT_MAX_RESULTS = 20;
    private static final Sort DEFAULT_SORT = Sort.by("title").ascending();

    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> booksDto = bookMapper.toDtoList(books);
        return booksDto;
    }

    public BookDto getBookById(String id) {
        Book book = findBookById(id);
        BookDto bookDto = bookMapper.toDto(book);
        return bookDto;
    }

    public BookResponseDto searchByTitle(String q, Integer startIndex, Integer maxResults) {
        Pageable pageable = createPageable(startIndex, maxResults);
        Page<Book> pageBook = bookRepository.findByTitleContaining(q, pageable);
        BookResponseDto bookResponseDto = bookMapper.toResponseDto(pageBook);
        return bookResponseDto;
    }

    public BookResponseDto searchByGenreId(List<String> genreIds, Integer startIndex,
            Integer maxResults) {
        Pageable pageable = createPageable(startIndex, maxResults);
        Page<Book> pageBook = bookRepositoryCustom.findByGenreIds(genreIds, pageable);
        BookResponseDto bookResponseDto = bookMapper.toResponseDto(pageBook);
        return bookResponseDto;
    }

    private Pageable createPageable(Integer startIndex, Integer maxResults) {
        startIndex = (startIndex != null) ? startIndex : DEFAULT_START_INDEX;
        maxResults = (maxResults != null) ? maxResults : DEFAULT_MAX_RESULTS;
        return PageRequest.of(startIndex, maxResults, DEFAULT_SORT);
    }

    private Book findBookById(String id) {
        Optional<Book> found = bookRepository.findById(id);
        if (found.isEmpty()) {
            throw new NotFoundException("Not found with this ID: " + id);
        }
        return found.get();
    }
}
