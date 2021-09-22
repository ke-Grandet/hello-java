package org.example.provider.controller;

import org.apache.ibatis.session.SqlSession;
import org.example.provider.mapper.MovieMapper;
import org.example.provider.pojo.dto.MovieDTO;
import org.example.provider.pojo.po.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieMapper movieMapper;

    @GetMapping("/getAll")
    public List<Movie> getAll() {
        return movieMapper.getAll();
    }

    @GetMapping("/get/{id}")
    public Movie get(@PathVariable String id) {
        return movieMapper.getOne(id);
    }

    @PostMapping("/add")
    public String add(@RequestBody MovieDTO movieDTO) {
        return movieMapper.insert(movieDTO);
    }
}
