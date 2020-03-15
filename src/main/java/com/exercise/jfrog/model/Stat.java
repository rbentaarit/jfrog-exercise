package com.exercise.jfrog.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Stat {

    private Integer downloads;

    public Stat() {
    }

    public Stat(Integer downloads) {
        this.downloads = downloads;
    }
}
