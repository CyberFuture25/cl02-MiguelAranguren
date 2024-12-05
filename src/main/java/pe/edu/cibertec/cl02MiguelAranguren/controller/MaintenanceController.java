package pe.edu.cibertec.cl02MiguelAranguren.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.cl02MiguelAranguren.dto.FilmDetailDto;
import pe.edu.cibertec.cl02MiguelAranguren.dto.FilmDto;
import pe.edu.cibertec.cl02MiguelAranguren.dto.LanguageDto;
import pe.edu.cibertec.cl02MiguelAranguren.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService; //de la interfaz

    //Model importado de spring y
    // retotrna string por la url
    @GetMapping("/start")
    public String start(Model model){

        //logica de control, no de negocio
       List<FilmDto> films = maintenanceService.getAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){

        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        model.addAttribute("filmDetailDto", filmDetailDto);
        return "maintenance-detail";
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        List<LanguageDto> languages = maintenanceService.getAllLanguages();

        model.addAttribute("filmDetailDto", filmDetailDto);
        model.addAttribute("languages", languages);
        return "maintenance-update";
    }

    @PostMapping("/updated")
    public String updateFilm(FilmDetailDto filmDetailDto) {
        maintenanceService.updateFilm(filmDetailDto);
        return "redirect:/maintenance/start";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        model.addAttribute("filmDetailDto", filmDetailDto);
        return "maintenance-delete";
    }

}
