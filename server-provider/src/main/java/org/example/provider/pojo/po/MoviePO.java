package org.example.provider.pojo.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MoviePO {
    private int id;
    private String selfId;
    private String name;
    private String director;
    private LocalDateTime releaseTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
