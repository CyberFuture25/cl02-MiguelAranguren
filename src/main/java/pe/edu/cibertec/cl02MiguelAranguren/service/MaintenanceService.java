package pe.edu.cibertec.cl02MiguelAranguren.service;

import pe.edu.cibertec.cl02MiguelAranguren.dto.FilmDetailDto;
import pe.edu.cibertec.cl02MiguelAranguren.dto.FilmDto;
import pe.edu.cibertec.cl02MiguelAranguren.dto.LanguageDto;

import java.util.List;


public interface MaintenanceService {

    // Es el llamado, no la implementacion
    List<FilmDto> getAllFilms();

    FilmDetailDto getFilmById(int id);

    void updateFilm(FilmDetailDto filmDetailDto);

    List<LanguageDto> getAllLanguages();


}
