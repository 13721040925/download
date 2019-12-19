package cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.pojo.Movie;

public interface MovieMapper {
	@Select(" select * from movie where type=#{type} ")
	List<Movie> selMovieByType(@Param("type") Integer type);

	@Select(" select * from movie where id=#{id} ")
	Movie getMovieById(@Param("id") Integer id);
}
