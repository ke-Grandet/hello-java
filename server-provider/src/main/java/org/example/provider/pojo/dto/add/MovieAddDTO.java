package org.example.provider.pojo.dto.add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("参数对象")
public class MovieAddDTO {

    private String selfId;

    private String name;

    private String director;
}
