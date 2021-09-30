package org.example.provider.mapper;

import org.apache.ibatis.annotations.*;
import org.example.provider.pojo.dto.page.MoviePageDTO;
import org.example.provider.pojo.po.MoviePO;

import java.util.List;

@Mapper
public interface MovieMapper {

    @Select("SELECT COUNT(*) FROM t_movie")
    int count();

    @Select("SELECT * FROM t_movie WHERE id = #{id}")
    @Results({
            @Result(property = "selfId", column = "self_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    MoviePO getById(@Param("id") int id);

    List<MoviePO> page(MoviePageDTO moviePageDTO);

    @Insert("INSERT INTO t_movie(self_id, name, director) VALUES (#{selfId}, #{name}, #{director})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(MoviePO moviePO);

    @Update("UPDATE t_movie SET name=#{name}, director=#{director} WHERE id=#{id}")
    void update(MoviePO moviePO);

    @Delete("DELETE FROM t_movie WHERE id=#{id}")
    void delete(String id);
}
