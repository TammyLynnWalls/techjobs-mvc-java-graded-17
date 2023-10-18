package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    //
    @PostMapping("/results")
    public String displaySearchResults(Model model, @RequestParam String searchType,  @RequestParam String searchTerm){
        ArrayList<Job> jobs = new ArrayList<>();
        if (searchType.equals("all") || searchTerm.equals("") || searchTerm.equals("all")){
           jobs = JobData.findAll();
//           model.addAttribute("title", "All Jobs" );
            model.addAttribute("title", "Jobs With All:");
        }else{
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("title", "Jobs With "+ columnChoices.get(searchType)+": "+ searchTerm);
        return "/search";

        //TODO FOR TAM: make it so the page title displays the column.value
        //TODO FOR TAM: make it so the ALL link says "ALL Jobs"
    }

}

