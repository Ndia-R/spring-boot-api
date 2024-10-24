package com.example.spring_boot_api.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> booksDto =
                books.stream().map(this::convertToDto).collect(Collectors.toList());
        return booksDto;
    }

    public BookDto getBookById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        BookDto bookDto = convertToDto(book);
        return bookDto;
    }

    public BookResponseDto searchByTitle(String q, Pageable pageable) {
        Page<Book> page = bookRepository.findByTitleContaining(q, pageable);

        Integer totalItems = (int) page.getTotalElements();
        List<Book> books = page.getContent();
        List<BookDto> booksDto =
                books.stream().map(this::convertToDto).collect(Collectors.toList());
        BookResponseDto bookResponseDto = new BookResponseDto(totalItems, booksDto);
        return bookResponseDto;
    }

    public BookResponseDto searchByGenreId(List<String> genreIds, Pageable pageable) {
        Page<Book> page = bookRepositoryCustom.findByGenreIds(genreIds, pageable);

        Integer totalItems = (int) page.getTotalElements();
        List<Book> books = page.getContent();
        List<BookDto> booksDto =
                books.stream().map(this::convertToDto).collect(Collectors.toList());
        BookResponseDto bookResponseDto = new BookResponseDto(totalItems, booksDto);
        return bookResponseDto;
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

    // private Book convertToEntity(BookDto bookDto) {
    // Book book = new Book();
    // String id = RandomStringUtil.generateRandomString();
    // book.setId(id);
    // book.setTitle(bookDto.getTitle());
    // book.setDescription(bookDto.getDescription());
    // book.setAuthors(bookDto.getAuthors());
    // book.setPublisher(book.getPublisher());
    // book.setPublishedDate(bookDto.getPublishedDate());
    // book.setPrice(bookDto.getPrice());
    // book.setPageCount(bookDto.getPageCount());
    // book.setIsbn(bookDto.getIsbn());
    // book.setImageUrl("http://vsv-peridot.skygroup.local/my-books/images/" + id + ".jpg");

    // book.setGenres(bookDto.getGenreIds().stream().map(genreId -> {
    // Genre genre = new Genre();
    // genre.setId(genreId);
    // return genre;
    // }).collect(Collectors.toSet()));
    // return book;
    // }
}
