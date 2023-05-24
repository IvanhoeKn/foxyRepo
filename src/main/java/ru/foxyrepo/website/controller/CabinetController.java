package ru.foxyrepo.website.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.foxyrepo.website.dao.*;
import ru.foxyrepo.website.model.Category;
import ru.foxyrepo.website.model.Profile;
import ru.foxyrepo.website.model.WriterRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j
public class CabinetController {

    @GetMapping({"/cabinet"})
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            String role = ((UserDetails)principal).getAuthorities().toString();
            System.out.println(role);
        } else {
            username = principal.toString();
        }
        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.findByUsername(username);
        model.addAttribute("profile", profile);
        ArticleDao articleDao = new ArticleDao();
        model.addAttribute("favoriteArticle", articleDao.getFavoritesForUser(profile.getIdProfile()));
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        model.addAttribute("categories", categoryList);
        Map<Long, String> categoryMap = new HashMap<>();
        for(Category category : categoryList) {
            categoryMap.put(category.getIdCategory(), category.getNameCategory());
        }
        model.addAttribute("selectCategory", categoryMap);
        model.addAttribute("publishedArticles", articleDao.getPublishedArticles(profile.getIdProfile()));
        model.addAttribute("requestWriter", profileDao.userRequestBecomeWriter());
        return "cabinet";
    }

    @PostMapping("/create-writer")
    public String askBecomeWriter(@RequestParam("idCreateWriter") Long idCreateWriter) {
        WriterRequest writerRequest = new WriterRequest();
        writerRequest.setIdUserProfile(idCreateWriter);
        WriterRequestDao writerRequestDao = new WriterRequestDao();
        writerRequestDao.persist(writerRequest);
        return "redirect:/cabinet";
    }

    @PostMapping("/confirm-writer")
    public String confirmWriter(@RequestParam("idConfirmWriter") Long idConfirmWriter) {
        WriterRequestDao writerRequestDao = new WriterRequestDao();
        WriterRequest writerRequest = writerRequestDao.findByUserId(idConfirmWriter);
        writerRequestDao.delete(writerRequest);
        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.findById(idConfirmWriter);
        StatusDao statusDao = new StatusDao();
        profile.setIdRole(statusDao.findById(2L).getIdStatus());
        profileDao.update(profile);
        return "redirect:/cabinet";
    }
}