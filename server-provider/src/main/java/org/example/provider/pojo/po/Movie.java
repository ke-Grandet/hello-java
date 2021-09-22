package org.example.provider.pojo.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Movie {
    private String id;
    private String name;
    private String director;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
