package com.example.spring_boot_api.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.spring_boot_api.dto.CreateGenreDto;
import com.example.spring_boot_api.dto.GenreDto;
import com.example.spring_boot_api.dto.UpdateGenreDto;
import com.example.spring_boot_api.model.Genre;
import com.example.spring_boot_api.service.GenreService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("")
    public ResponseEntity<List<GenreDto>> getGenres() {
        List<GenreDto> genres = genreService.getGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Integer id) {
        GenreDto genre = genreService.getGenreById(id);
        return ResponseEntity.ok(genre);
    }

    @PostMapping("")
    public ResponseEntity<GenreDto> createGenre(@Valid @RequestBody CreateGenreDto dto) {
        GenreDto genre = genreService.createGenre(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(genre.getId()).toUri();
        return ResponseEntity.created(location).body(genre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genre> deleteGenre(@PathVariable Integer id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable Integer id,
            @Valid @RequestBody UpdateGenreDto dto) {
        genreService.updateGenre(id, dto);
        return ResponseEntity.noContent().build();
    }
}
