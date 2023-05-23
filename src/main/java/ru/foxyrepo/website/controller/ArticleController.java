package ru.foxyrepo.website.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.foxyrepo.website.dao.ArticleDao;
import ru.foxyrepo.website.dao.CategoryDao;
import ru.foxyrepo.website.dao.FavoritesDao;
import ru.foxyrepo.website.dao.ProfileDao;
import ru.foxyrepo.website.model.Favorites;
import ru.foxyrepo.website.model.Profile;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Log4j
public class ArticleController {
    @RequestMapping(value = "/articles", method = GET)
    @ResponseBody
    public ModelAndView showArticle(@RequestParam("id") Long id) {
        ArticleDao articleDao = new ArticleDao();
        ModelAndView modelAndView = new ModelAndView("article");
        modelAndView.addObject("article", articleDao.findById(id));
        CategoryDao categoryDao = new CategoryDao();
        modelAndView.addObject("category", categoryDao.findById(articleDao.findById(id).getIdClass()));
        return modelAndView;
    }

    @PostMapping("/like")
    public String addLike(@RequestParam("idArticle") Long idArticle, @RequestParam("user") String username) {
        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.findByUsername(username);
        Favorites favorites = new Favorites();
        favorites.setIdWhat(idArticle);
        favorites.setIdUserFor(profile.getIdProfile());
        FavoritesDao favoritesDao = new FavoritesDao();
        favoritesDao.persist(favorites);
        return "redirect:/articles?id=" + idArticle;
    }
}
