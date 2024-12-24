package org.example.demo.controller;

import org.example.demo.dao.DaoFactory;
import org.example.demo.dao.PlatformDao;
import org.example.demo.entity.Game;
import org.example.demo.entity.Platform;
import org.example.demo.form.AddPlatformForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlatformController {
    private PlatformDao platformDao = DaoFactory.getDaoFactory().getPlatformDao();

    @RequestMapping(value = { "/", "/platforms" }, method = { RequestMethod.GET })
    public String showPlatforms(Model model) {
        List<Platform> platforms = platformDao.selectAll();
        model.addAttribute("allPlatforms", platforms);
        return "platformsPage";
    }

    @RequestMapping(value = { "/add-platform" }, method = { RequestMethod.GET })
    public String showAddPlatformPage(Model model, AddPlatformForm form) {
        model.addAttribute("addPlatformForm");
        return "addPlatformPage";
    }

    @RequestMapping(value = { "/add-platform" }, method = { RequestMethod.POST })
    public String addPlatform(Model model, AddPlatformForm form) {
        platformDao.insertIntoPlatform(form.getName());
        return "redirect:/platforms";
    }

    @GetMapping(value = {"/update-platform/{platformId}"})
    public String showUpdatePlatformPage(@PathVariable int platformId, ModelMap model) {
        Platform platformToUpdate = platformDao.selectPlatformById(platformId);
        model.addAttribute("platformToUpdate", platformToUpdate);
        return "UpdatePlatformPage";
    }

    @PostMapping(value = {"/update-platform"})
    public String updatePlatform(ModelMap model, @ModelAttribute Platform platform) {
        platformDao.updateNameById(platform);
        return "redirect:/platforms";
    }

    @DeleteMapping(value = { "/delete-platform/{platformId}" })
    public String deletePlatform(@PathVariable int platformId) {
        platformDao.deleteFromPlatformById(platformId);
        return "redirect:/platforms";
    }
}
