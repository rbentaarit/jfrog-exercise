package com.exercise.jfrog.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Result {

    private String repo;
    private String name;
    private List<Stat> stats = new ArrayList<>();

}
