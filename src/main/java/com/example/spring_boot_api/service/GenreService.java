package com.example.spring_boot_api.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_boot_api.dto.GenreDto;
import com.example.spring_boot_api.model.Genre;
import com.example.spring_boot_api.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDto> getGenres() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDto> genresDto =
                genres.stream().map(this::convertToDto).collect(Collectors.toList());
        return genresDto;
    }

    public GenreDto getGenreById(Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        GenreDto genreDto = new GenreDto(genre);
        return genreDto;
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    private GenreDto convertToDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }
}
