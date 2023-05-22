package ru.foxyrepo.website.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.foxyrepo.website.dao.ArticleDao;

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
        return modelAndView;
    }
}
