package org.example.provider.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieDTO {
    private String id;
    private String name;
    private String director;
}
