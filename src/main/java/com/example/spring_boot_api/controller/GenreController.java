package com.example.spring_boot_api.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.spring_boot_api.dto.GenreDto;
import com.example.spring_boot_api.model.Genre;
import com.example.spring_boot_api.service.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<GenreDto> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping("/{id}")
    public GenreDto getGenreById(@PathVariable Integer id) {
        return genreService.getGenreById(id);
    }

    @PostMapping
    public ResponseEntity<Genre> createTodoItem(@RequestBody Genre newGenre) {
        Genre genre = genreService.saveGenre(newGenre);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(genre.getId()).toUri();
        return ResponseEntity.created(location).body(genre);
    }
}
