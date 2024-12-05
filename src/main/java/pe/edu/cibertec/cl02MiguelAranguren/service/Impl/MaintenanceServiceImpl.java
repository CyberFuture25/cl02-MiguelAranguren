package pe.edu.cibertec.cl02MiguelAranguren.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.cl02MiguelAranguren.dto.FilmDetailDto;
import pe.edu.cibertec.cl02MiguelAranguren.dto.FilmDto;
import pe.edu.cibertec.cl02MiguelAranguren.dto.LanguageDto;
import pe.edu.cibertec.cl02MiguelAranguren.entity.Film;
import pe.edu.cibertec.cl02MiguelAranguren.entity.Language;
import pe.edu.cibertec.cl02MiguelAranguren.repository.FilmRepository;
import pe.edu.cibertec.cl02MiguelAranguren.repository.LanguageRepository;
import pe.edu.cibertec.cl02MiguelAranguren.service.MaintenanceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//Para que esté en contexto de spring
@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    //Me permite inyectar memomoria (inyeccion de dependencias)
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    LanguageRepository languageRepository;

    @Override
    public List<FilmDto> getAllFilms() {

        // para retornar el objetos
        List<FilmDto> films = new ArrayList<FilmDto>();
        //iteracion de los elementos
        Iterable<Film> iterable = filmRepository.findAll();

        // Rellenando el objeto a retornar
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getF1ilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalRate());
            films.add(filmDto);
        });

        return films;
    }

    @Override
    public FilmDetailDto getFilmById(int id) {

        Optional<Film> optional = filmRepository.findById(id);

        return optional.map(
                film -> new FilmDetailDto(
                        film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getLanguage().getLanguageId(),
                        film.getLanguage().getName(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate()
                )
        ).orElse(null);


    }

    @Override
    public void updateFilm(FilmDetailDto filmDetailDto) {

        Optional<Film> optionalFilm = filmRepository.findById(filmDetailDto.filmId());
        Optional<Language> optionalLanguage = languageRepository.findById(filmDetailDto.lenguageId());

        if (optionalFilm.isPresent() && optionalLanguage.isPresent()) {
            Film film = optionalFilm.get();
            Language language = optionalLanguage.get();

            film.setTitle(filmDetailDto.title());
            film.setDescription(filmDetailDto.description());
            film.setReleaseYear(filmDetailDto.releaseYear());
            film.setLanguage(language);
            film.setRentalDuration(filmDetailDto.rentalDuration());
            film.setRentalRate(filmDetailDto.rentalRate());
            film.setLength(filmDetailDto.length());
            film.setReplacementCost(filmDetailDto.replacementCost());
            film.setRating(filmDetailDto.rating());
            film.setSpecialFeatures(filmDetailDto.specialFeatures());
            film.setLastUpdate(new Date());

            filmRepository.save(film);
        }

    }


    @Override
    public List<LanguageDto> getAllLanguages() {

        List<LanguageDto> languages = new ArrayList<LanguageDto>();
        Iterable<Language> iterable = languageRepository.findAll();
        iterable.forEach(language -> {
            LanguageDto languageDto = new LanguageDto(language.getLanguageId(),
                    language.getName());
            languages.add(languageDto);
        });
        return languages;
    }


}