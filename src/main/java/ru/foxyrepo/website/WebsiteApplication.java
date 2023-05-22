package ru.foxyrepo.website;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.foxyrepo.website.dao.ArticleDao;
import ru.foxyrepo.website.dao.CategoryDao;
import ru.foxyrepo.website.dao.StatusDao;
import ru.foxyrepo.website.model.Category;
import ru.foxyrepo.website.model.Status;

import java.util.List;

@SpringBootApplication
@Log4j
public class WebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }

}
