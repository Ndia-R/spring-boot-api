package com.example.spring_boot_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.spring_boot_api.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Page<Book> findByTitleContaining(String q, Pageable pageable);
}
