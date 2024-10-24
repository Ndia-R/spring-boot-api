package com.example.spring_boot_api.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.spring_boot_api.model.Book;

public interface BookRepositoryCustom {
    Page<Book> findByGenreIds(List<String> genreIds, Pageable pageable);
}
