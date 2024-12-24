package org.example.demo.controller;

import org.example.demo.dao.DaoFactory;
import org.example.demo.dao.GameDao;
import org.example.demo.dao.GenreDao;
import org.example.demo.dao.PlatformDao;
import org.example.demo.entity.Game;
import org.example.demo.form.AddUpdateGameForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {
    private final GameDao gameDao = DaoFactory.getDaoFactory().getGameDao();
    private final GenreDao genreDao = DaoFactory.getDaoFactory().getGenreDao();
    private final PlatformDao platformDao = DaoFactory.getDaoFactory().getPlatformDao();

    @RequestMapping(value = {"/games"}, method = {RequestMethod.GET})
    public String showGames(Model model) {
        List<Game> games = gameDao.selectAll();
        model.addAttribute("allGames", games);
        return "gamesPage";
    }

    @RequestMapping(value = {"/add-game"}, method = {RequestMethod.GET})
    public String showAddGamePage(Model model) {
        AddUpdateGameForm addUpdateGameForm = new AddUpdateGameForm();
        model.addAttribute("addUpdateGameForm", addUpdateGameForm);
        return "addGamePage";
    }

    @RequestMapping(value = {"/add-game"}, method = {RequestMethod.POST})
    public String addGame(Model model, AddUpdateGameForm form) {
        gameDao.insertIntoGame(form.getName(), form.getPublisher(), form.getReleaseYear(),
                form.getGenreId(), form.getPrice(), form.getPlatformId());
        return "redirect:/games";
    }

    @GetMapping(value = {"/update-game/{gameId}"})
    public String showUpdateGamePage(@PathVariable int gameId, ModelMap model) {
        Game gameToUpdate = gameDao.selectGameById(gameId);
        model.addAttribute("gameToUpdate", gameToUpdate);
        model.addAttribute("allGenres", genreDao.selectAll());
        model.addAttribute("allPlatforms", platformDao.selectAll());
        return "UpdateGamePage";
    }

    @PostMapping(value = {"/update-game"})
    public String updateGame(ModelMap model, @ModelAttribute Game game) {
        gameDao.updateGame(game);
        return "redirect:/games";
    }

    @DeleteMapping(value = {"/delete-game/{gameId}"})
    public String deleteGame(@PathVariable int gameId) {
        gameDao.deleteFromGamesById(gameId);
        return "redirect:/games";
    }
}
