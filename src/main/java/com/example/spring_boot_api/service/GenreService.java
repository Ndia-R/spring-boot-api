package com.example.spring_boot_api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.example.spring_boot_api.dto.genre.GenreCreateDto;
import com.example.spring_boot_api.dto.genre.GenreDto;
import com.example.spring_boot_api.dto.genre.GenreUpdateDto;
import com.example.spring_boot_api.exception.NotFoundException;
import com.example.spring_boot_api.mapper.GenreMapper;
import com.example.spring_boot_api.model.Genre;
import com.example.spring_boot_api.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Cacheable("getGenres")
    public List<GenreDto> getGenres() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDto> genresDto = genreMapper.toDtoList(genres);
        return genresDto;
    }

    @Cacheable(value = "getGenreById", key = "#p0")
    public GenreDto getGenreById(Integer id) {
        Genre genre = findGenreById(id);
        GenreDto genreDto = genreMapper.toDto(genre);
        return genreDto;
    }

    @CacheEvict(value = "getGenres", allEntries = true)
    public GenreDto createGenre(GenreCreateDto dto) {
        Genre genre = genreMapper.CreateDtoToEntity(dto);
        Genre saveGenre = genreRepository.save(genre);
        GenreDto genreDto = genreMapper.toDto(saveGenre);
        return genreDto;
    }

    @Caching(evict = {@CacheEvict(value = "getGenreById", key = "#p0"),
            @CacheEvict(value = "getGenres", allEntries = true)})
    public void updateGenre(Integer id, GenreUpdateDto dto) {
        Genre genre = findGenreById(id);
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        genreRepository.save(genre);
    }

    @Caching(evict = {@CacheEvict(value = "getGenreById", key = "#p0"),
            @CacheEvict(value = "getGenres", allEntries = true)})
    public void patchGenre(Integer id, Map<String, Object> updates) {
        Genre genre = findGenreById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    genre.setName((String) value);
                    break;
                case "description":
                    genre.setDescription((String) value);
                    break;
            }
        });
        genreRepository.save(genre);
    }

    @Caching(evict = {@CacheEvict(value = "getGenreById", key = "#p0"),
            @CacheEvict(value = "getGenres", allEntries = true)})
    public void deleteGenre(Integer id) {
        Genre genre = findGenreById(id);
        genreRepository.delete(genre);
    }

    private Genre findGenreById(Integer id) {
        Optional<Genre> found = genreRepository.findById(id);
        if (found.isEmpty()) {
            throw new NotFoundException("Not found with this ID: " + id);
        }
        return found.get();
    }
}
