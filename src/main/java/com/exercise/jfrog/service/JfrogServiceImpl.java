package com.exercise.jfrog.service;

import com.exercise.jfrog.model.ArtifactResult;
import com.exercise.jfrog.model.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JfrogServiceImpl implements JfrogService {
    public static final String query = "items.find({\n" +
        "\"repo\":{\"$eq\":\"%s\"},\n" +
        "\"name\": {\"$match\" : \"*.jar\"}\n" +
        "}).include(\"name\", \"repo\", \"stat.downloads\").sort({\"$desc\": [\"stat.downloads\"]})";

    private final JfrogClient jfrogClient;

    public JfrogServiceImpl(JfrogClient jfrogClient) {
        this.jfrogClient = jfrogClient;
    }

    @Override
    public List<Result> getMostImportant(String repoName) {
        String formattedQuery = String.format(query,repoName);
        ArtifactResult result = jfrogClient.getSorted(formattedQuery);
        return filter2mostDownloaded(result.getResults());
    }

    @Override
    public List<Result> getAllJarRepo(String repoName) {
        String formattedQuery = String.format(query,repoName);
        return sortDesc(jfrogClient.getSorted(formattedQuery).getResults());
    }


    private int secondMax(List<Result> results){
        int max = results.get(0).getStats().get(0).getDownloads();
        int secondMax = max;
        if (results.size() > 1){
            Result res = results.stream().filter(result -> result.getStats().get(0).getDownloads() < max).findFirst().get();
            secondMax = res.getStats().get(0).getDownloads();
        }
        return secondMax;
    }


    private List<Result> filter2mostDownloaded(List<Result> results){
        if (results.size() > 0){
        List<Result> sortedList = sortDesc(results);
        int secondMax = secondMax(sortedList);
        return sortedList.stream().filter(result -> result.getStats().get(0).getDownloads() >= secondMax).collect(Collectors.toList()) ;}
        return results;
    }

    private List<Result> sortDesc(List<Result> results){
        return results.stream().sorted((o1, o2) -> o2.getStats().get(0).getDownloads() - o1.getStats().get(0).getDownloads()).collect(Collectors.toList());
    }

}
