package com.example.spring_boot_api.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.spring_boot_api.dto.BookDto;
import com.example.spring_boot_api.dto.BookResponseDto;
import com.example.spring_boot_api.model.Book;
import com.example.spring_boot_api.repository.BookRepository;
import com.example.spring_boot_api.repository.BookRepositoryCustom;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRepositoryCustom bookRepositoryCustom;

    private static final Integer DEFAULT_START_INDEX = 0;
    private static final Integer DEFAULT_MAX_RESULTS = 20;
    private static final Sort DEFAULT_SORT = Sort.by("title").ascending();

    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        return convertToDtoList(books);
    }

    public BookDto getBookById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToDto(book);
    }

    public BookResponseDto searchByTitle(String q, Integer startIndex, Integer maxResults) {
        Pageable pageable = createPageable(startIndex, maxResults);
        Page<Book> page = bookRepository.findByTitleContaining(q, pageable);
        return createBookResponse(page);
    }

    public BookResponseDto searchByGenreId(List<String> genreIds, Integer startIndex,
            Integer maxResults) {
        Pageable pageable = createPageable(startIndex, maxResults);
        Page<Book> page = bookRepositoryCustom.findByGenreIds(genreIds, pageable);
        return createBookResponse(page);
    }

    private Pageable createPageable(Integer startIndex, Integer maxResults) {
        startIndex = (startIndex != null) ? startIndex : DEFAULT_START_INDEX;
        maxResults = (maxResults != null) ? maxResults : DEFAULT_MAX_RESULTS;
        return PageRequest.of(startIndex, maxResults, DEFAULT_SORT);
    }

    private BookResponseDto createBookResponse(Page<Book> page) {
        Integer totalItems = (int) page.getTotalElements();
        List<BookDto> booksDto = convertToDtoList(page.getContent());
        return new BookResponseDto(totalItems, booksDto);
    }

    private List<BookDto> convertToDtoList(List<Book> books) {
        return books.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setDescription(book.getDescription());
        bookDto.setGenreIds(Arrays.stream(book.getGenreIds().split(",")).map(Integer::parseInt)
                .collect(Collectors.toList()));
        bookDto.setAuthors(Arrays.asList(book.getAuthors().split(",")));
        bookDto.setPublisher(book.getPublisher());
        bookDto.setPublishedDate(book.getPublishedDate());
        bookDto.setPrice(book.getPrice());
        bookDto.setPageCount(book.getPageCount());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setImageUrl(book.getImageUrl());
        return bookDto;
    }
}
