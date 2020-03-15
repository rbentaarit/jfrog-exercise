package com.exercise.jfrog.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ArtifactResult {

    private List<Result> results = new ArrayList<>();
    private Range range;

}
