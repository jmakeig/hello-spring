package com.marklogic.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    //private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private KnowledgeBase kb;

    @Autowired
    public HomeController(KnowledgeBase kb) {
        this.kb = kb;
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, @RequestParam(value = "q", defaultValue = "") String query) {

        List<Entry> entries = kb.findEntries(query);
        model.addAttribute("entries", entries);
        model.addAttribute("query", query);
        return "home"; // view name
    }

}
