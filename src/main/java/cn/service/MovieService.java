package cn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Movie;

public interface MovieService {
	List<Movie> selMovieByType(Integer type);

	Movie getMovieById(@Param("id") Integer id);
}
