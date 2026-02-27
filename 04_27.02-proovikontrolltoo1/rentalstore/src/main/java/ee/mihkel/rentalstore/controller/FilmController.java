package ee.mihkel.rentalstore.controller;

import ee.mihkel.rentalstore.dto.FilmSaveDto;
import ee.mihkel.rentalstore.entity.Film;
import ee.mihkel.rentalstore.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmRepository filmRepository;

    @PostMapping("films")
    public Film saveFilm(@RequestBody FilmSaveDto filmSaveDto){
        Film film = new Film();
        film.setTitle(filmSaveDto.title());
        film.setType(filmSaveDto.type());
        film.setDays(0); // <--- selle nimel tegime Recordi
        return filmRepository.save(film);
    }

    @DeleteMapping("films/{id}")
    public void deleteFilm(@PathVariable Long id){
        filmRepository.deleteById(id);
    }
}
