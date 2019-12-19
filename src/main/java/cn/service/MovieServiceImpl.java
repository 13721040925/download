package cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.MovieMapper;
import cn.pojo.Movie;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
	@Resource
	private MovieMapper mapper;
	@Override
	public List<Movie> selMovieByType(Integer type) {
		// TODO Auto-generated method stub
		return mapper.selMovieByType(type);
	}

	@Override
	public Movie getMovieById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getMovieById(id);
	}

}
