package ru.foxyrepo.website.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.foxyrepo.website.dao.ArticleDao;
import ru.foxyrepo.website.dao.CategoryDao;
import ru.foxyrepo.website.model.Article;
import ru.foxyrepo.website.model.Category;
import ru.foxyrepo.website.model.Profile;
import ru.foxyrepo.website.service.SaveNewUser;
import ru.foxyrepo.website.service.SecurityService;
import ru.foxyrepo.website.util.UserValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Log4j
public class MainController {

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        model.addAttribute("categories", categoryList);
        Map<Long, String> categoryMap = new HashMap<>();
        for(Category category : categoryList) {
            categoryMap.put(category.getIdCategory(), category.getNameCategory());
        }
        model.addAttribute("selectCategory", categoryMap);
        ArticleDao articleDao = new ArticleDao();
        model.addAttribute("lastAdded", articleDao.getLastAdded());
        model.addAttribute("favoriteArticle", articleDao.getBestFavorites());
        model.addAttribute("userForm", new Profile());
        return "index";
    }

    @RequestMapping(value = "/all", method = GET)
    @ResponseBody
    public ModelAndView all(@RequestParam(value = "filter", required = false) Long filter) {
        ModelAndView modelAndView = new ModelAndView("all");
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        modelAndView.addObject("categories", categoryList);
        Map<Long, String> categoryMap = new HashMap<>();
        for (Category category : categoryList) {
            categoryMap.put(category.getIdCategory(), category.getNameCategory());
        }
        modelAndView.addObject("selectCategory", categoryMap);
        ArticleDao articleDao = new ArticleDao();
        if (filter != null) {
            modelAndView.addObject("articles", articleDao.getArticlesWithCategory(filter));
        }
        else {
            modelAndView.addObject("articles", articleDao.findAll());
        }
        return modelAndView;
    }
    @GetMapping({"/popular"})
    public String popular(Model model) {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        model.addAttribute("categoryList", categoryList);
        Map<Long, List<Article>> articlesFilteredWithCategory = new HashMap<>();
        ArticleDao articleDao = new ArticleDao();
        for (Category category : categoryList) {
            List<Article> tmp = articleDao.getPopularArticleWithFilter(category.getIdCategory());
            if (!tmp.isEmpty()) {
                articlesFilteredWithCategory.put(category.getIdCategory(), tmp);
            }
        }
        model.addAttribute("articleFiltered", articlesFilteredWithCategory);
        return "popular";
    }

    @GetMapping({"/about"})
    public String about(Model model) {
        return "about";
    }
}
