package org.example.provider.service;

import com.github.dozermapper.core.Mapper;
import org.example.provider.mapper.MovieMapper;
import org.example.provider.pojo.dto.add.MovieAddDTO;
import org.example.provider.pojo.dto.page.MoviePageDTO;
import org.example.provider.pojo.po.MoviePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    Mapper dozerBeanMapper;

    /**
     * 条件分页查询
     * @param moviePageDTO 条件参数
     * @return 查询结果
     */
    public Map<String, Object> page(MoviePageDTO moviePageDTO) {
        Map<String, Object> map = new HashMap<>();
        List<MoviePO> list = movieMapper.page(moviePageDTO);
        map.put("list", list);
        map.put("pageNum", moviePageDTO.getPageNum());
        map.put("pageSize", moviePageDTO.getPageSize());
        map.put("total", movieMapper.count());
        return map;
    }

    /**
     * 添加
     * @return id
     * @param movieAddDTO 参数对象
     */
    public int add(MovieAddDTO movieAddDTO) {
        MoviePO moviePO = dozerBeanMapper.map(movieAddDTO, MoviePO.class);
        movieMapper.insert(moviePO);
        return moviePO.getId();
    }

    /**
     * 修改
     * @param movieAddDTO 参数对象
     */
    public void update(MovieAddDTO movieAddDTO) {
        // todo
    }

    /**
     * 删除
     * @param id 主键
     */
    public void delete(int id) {
        // todo
    }
}
