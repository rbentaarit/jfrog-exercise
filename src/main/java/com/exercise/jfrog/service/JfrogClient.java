package com.exercise.jfrog.service;

import com.exercise.jfrog.configuration.JfrogConfiguration;
import com.exercise.jfrog.model.ArtifactResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name="ArtifactoryClient", url = "${jfrog.url}", configuration = JfrogConfiguration.class)
public interface JfrogClient {

    @PostMapping(value="/artifactory/api/search/aql")
    ArtifactResult getSorted(String query);

}
