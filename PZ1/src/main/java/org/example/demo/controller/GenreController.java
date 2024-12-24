package org.example.demo.controller;

import org.example.demo.dao.DaoFactory;
import org.example.demo.dao.GenreDao;
import org.example.demo.entity.Genre;
import org.example.demo.entity.Platform;
import org.example.demo.form.AddGenreForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GenreController {
    private GenreDao genreDao = DaoFactory.getDaoFactory().getGenreDao();

    @RequestMapping(value = { "/genres" }, method = { RequestMethod.GET })
    public String showGenres(Model model) {
        List<Genre> genres = genreDao.selectAll();
        model.addAttribute("allGenres", genres);
        return "genresPage";
    }

    @RequestMapping(value = { "/add-genre" }, method = { RequestMethod.GET })
    public String showAddGenrePage(Model model, AddGenreForm form) {
        model.addAttribute("addGenreForm");
        return "addGenrePage";
    }

    @RequestMapping(value = { "/add-genre" }, method = { RequestMethod.POST })
    public String addGenre(Model model, AddGenreForm form) {
        genreDao.insertIntoGenres(form.getName());
        return "redirect:/genres";
    }

    @GetMapping(value = {"/update-genre/{genreId}"})
    public String showUpdateGenrePage(@PathVariable int genreId, ModelMap model) {
        Genre genreToUpdate = genreDao.getGenreById(genreId);
        model.addAttribute("genreToUpdate", genreToUpdate);
        return "UpdateGenrePage";
    }

    @PostMapping(value = {"/update-genre"})
    public String updateGenre(ModelMap model, @ModelAttribute Genre genre) {
        genreDao.updateNameById(genre);
        return "redirect:/genres";
    }

    @DeleteMapping(value = { "/delete-genre/{genreId}" })
    public String deleteGenre(@PathVariable int genreId) {
        genreDao.deleteFromGenresById(genreId);
        return "redirect:/genres";
    }
}
