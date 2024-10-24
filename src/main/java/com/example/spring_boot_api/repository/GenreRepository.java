package com.example.spring_boot_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.spring_boot_api.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
