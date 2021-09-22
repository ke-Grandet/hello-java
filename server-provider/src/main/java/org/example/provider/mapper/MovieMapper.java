package org.example.provider.mapper;

import org.apache.ibatis.annotations.*;
import org.example.provider.pojo.dto.MovieDTO;
import org.example.provider.pojo.po.Movie;

import java.util.List;

@Mapper
public interface MovieMapper {

    @Select("SELECT * FROM t_movie")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<Movie> getAll();

    @Select("SELECT * FROM t_movie WHERE id = #{id}")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    Movie getOne(@Param("id") String id);

    @Insert("INSERT INTO t_movie(id, name, director) VALUES (#{id}, #{name}, #{director})")
    String insert(MovieDTO movieDTO);

    @Update("UPDATE t_movie SET name=#{name}, director=#{director} WHERE id=#{id}")
    String update(Movie movie);

    @Delete("DELETE FROM t_movie WHERE id=#{id}")
    String delete(String id);
}
