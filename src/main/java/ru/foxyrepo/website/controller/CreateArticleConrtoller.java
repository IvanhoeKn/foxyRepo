package ru.foxyrepo.website.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.foxyrepo.website.dao.ArticleDao;
import ru.foxyrepo.website.dao.CategoryDao;
import ru.foxyrepo.website.dao.ProfileDao;
import ru.foxyrepo.website.model.Article;
import ru.foxyrepo.website.model.Category;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j
public class CreateArticleConrtoller {

    @GetMapping({"/writing-article"})
    public String index(Model model) {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        Map<Long, String> categoryMap = new HashMap<>();
        for(Category category : categoryList) {
            categoryMap.put(category.getIdCategory(), category.getNameCategory());
        }
        model.addAttribute("selectCategory", categoryMap);
        return "writing_article";
    }

    @PostMapping("/writing-article")
    public String handleArticleSubmission(@RequestParam("title") String title,
                                          @RequestParam("summary") String summary,
                                          @RequestParam(value = "document", required = false) String document,
                                          @RequestParam("category") String category,
                                          @RequestParam("user") String username) {
        Article article = new Article();
        article.setTitle(title);
        article.setSummary(summary);
        if (document != null) {
            article.setDocument(document);
        }
        article.setPicture("images/test.jpg");
        article.setIdClass(Long.parseLong(category));
        ProfileDao profileDao = new ProfileDao();
        article.setIdWriter(profileDao.findByUsername(username).getIdProfile());
        article.setLikeCounter(0L);
        article.setCreationDate(Timestamp.from(Instant.now()));
        ArticleDao articleDao = new ArticleDao();
        articleDao.persist(article);
        return "redirect:/articles?id=" + article.getIdArticle();
    }
}
