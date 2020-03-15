package com.exercise.jfrog.service;


import com.exercise.jfrog.model.ArtifactResult;
import com.exercise.jfrog.model.Range;
import com.exercise.jfrog.model.Result;
import com.exercise.jfrog.model.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ActiveProfiles("test")
class JfrogServiceImplTest {

    @MockBean
    JfrogClient jfrogClient;

    @Autowired
    JfrogService jfrogService;

    String repoName = "jcenter-cache";

    @Before
    public void setup() {
        Mockito.when(jfrogClient.getSorted(anyString())).thenReturn(null);
    }



    @Test
    void getMostImportant() {
        Mockito.when(jfrogClient.getSorted(anyString())).thenReturn(getResults());
        List<Result> res = jfrogService.getMostImportant(repoName);
        Assert.assertEquals(res.size(), 2);

    }

    @Test
    void getMostImportant_one_element() {
        Mockito.when(jfrogClient.getSorted(anyString())).thenReturn(unique());
        List<Result> res = jfrogService.getMostImportant(repoName);
        Assert.assertEquals(res.size(), 1);
    }

    @Test
    void getMostImportant_empty() {
        Mockito.when(jfrogClient.getSorted(anyString())).thenReturn(empty());
        List<Result> res = jfrogService.getMostImportant(repoName);
        Assert.assertEquals(res.size(), 0);
    }

    private ArtifactResult getResults(){
        ArtifactResult artifactResult = new ArtifactResult();
        artifactResult.setRange(new Range());
        artifactResult.setResults(
        Arrays.asList(
        Result.builder().name("repo.jar").repo("/path/repo").stats(Arrays.asList(Stat.builder().downloads(34).build())).build(),
        Result.builder().name("repo2.jar").repo("/path/repo2").stats(Arrays.asList(Stat.builder().downloads(35).build())).build())
       );
        return artifactResult;
    }

    private ArtifactResult unique(){
        ArtifactResult artifactResult = new ArtifactResult();
        artifactResult.setRange(new Range());
        artifactResult.setResults(
            Arrays.asList(
                Result.builder().name("repo.jar").repo("/path/repo").stats(Arrays.asList(Stat.builder().downloads(34).build())).build())
        );
        return artifactResult;
    }

    private ArtifactResult empty(){
        ArtifactResult artifactResult = new ArtifactResult();
        artifactResult.setRange(new Range());
        artifactResult.setResults(
            new ArrayList<>()
        );
        return artifactResult;
    }
}
