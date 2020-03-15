package com.exercise.jfrog.controller;

import com.exercise.jfrog.model.ArtifactResult;
import com.exercise.jfrog.model.Result;
import com.exercise.jfrog.service.JfrogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JfrogController {

    private final JfrogService jfrogService;

    public JfrogController(JfrogService jfrogService) {
        this.jfrogService = jfrogService;
    }

    @GetMapping("/result")
    public List<Result> getMostImportantJars(@RequestParam(defaultValue = "jcenter-cache", required = false) String repoName){
        return jfrogService.getMostImportant(repoName);
    }

    @GetMapping("/all")
    public List<Result> getAllJars(@RequestParam(defaultValue = "jcenter-cache", required = false) String repoName){
        return jfrogService.getAllJarRepo(repoName);
    }
}
