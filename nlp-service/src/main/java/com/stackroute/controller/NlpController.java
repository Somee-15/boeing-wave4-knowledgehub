package com.stackroute.controller;

import com.stackroute.domain.NlpResult;
import com.stackroute.domain.SearchJSON;
import com.stackroute.service.NlpService;
import com.stackroute.service.QuestionStorageService;
import com.stackroute.service.SearchJSONProviderService;
import com.stackroute.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class NlpController {
    private SearchService searchService;
    private NlpService nlpService;
    private SearchJSONProviderService searchJSONProviderService;
    private QuestionStorageService questionStorageService;

    @Autowired
    public NlpController(QuestionStorageService questionStorageService,SearchJSONProviderService searchJSONProviderService, SearchService searchService, NlpService nlpService) {
        this.searchService = searchService;
        this.nlpService = nlpService;
        this.searchJSONProviderService = searchJSONProviderService;
        this.questionStorageService = questionStorageService;
    }

    @PostMapping("searchString")
    public ResponseEntity<?> setParagraph(@RequestBody SearchJSON searchJSON) {
        ResponseEntity responseEntity;
        try {
            questionStorageService.saveQuestion(searchJSON.getSearchString());
            searchService.takeSearchJSON(searchJSON);
            searchJSONProviderService.setSearchJSON(searchJSON);

            responseEntity = new ResponseEntity<String>("SearchString is successfully taken.", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("SearchString is not taken.", HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
    }

    @GetMapping("nlpResults")
    public ResponseEntity<NlpResult> getAnalysisResult() {
        ResponseEntity responseEntity;
        try {

            NlpResult nlpResult;
            nlpResult = nlpService.getNlpResults();
            return new ResponseEntity<NlpResult>(nlpResult, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("No results found.", HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
    }
}
