package org.example.provider.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.provider.pojo.dto.add.MovieAddDTO;
import org.example.provider.pojo.dto.page.MoviePageDTO;
import org.example.provider.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @ApiOperation("分页查询")
    @PostMapping("/page")
    public Map<String, Object> page(@RequestBody MoviePageDTO moviePageDTO) {
        return movieService.page(moviePageDTO);
    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public int add(@RequestBody MovieAddDTO movieAddDTO) {
        return movieService.add(movieAddDTO);
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public void update(@RequestBody MovieAddDTO movieAddDTO) {
        movieService.update(movieAddDTO);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        movieService.delete(id);
    }
}
