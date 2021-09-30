package org.example.provider.pojo.dto.page;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MoviePageDTO {

    private int pageNum;

    private int pageSize;

    private int id;

    private String selfId;

    private String name;

    private String director;

    private LocalDateTime releaseTimeBegin;

    private LocalDateTime releaseTimeEnd;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
