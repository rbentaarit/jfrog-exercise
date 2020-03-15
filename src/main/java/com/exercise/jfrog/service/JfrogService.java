package com.exercise.jfrog.service;

import com.exercise.jfrog.model.Result;
import java.util.List;

public interface JfrogService {

    public List<Result> getMostImportant(String repoName);
    public List<Result> getAllJarRepo(String repoName);
}
