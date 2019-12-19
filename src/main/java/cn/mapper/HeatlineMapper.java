package cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.pojo.Heatline;

public interface HeatlineMapper {
	@Insert(" insert into heatline (title,category,author_name,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03) values (#{title},#{category},#{author_name},#{url},#{thumbnail_pic_s},#{thumbnail_pic_s02},#{thumbnail_pic_s03}) ")
	int addHeatline(Heatline heatline);

	@Select(" select * from heatline where category=#{category} ORDER BY RAND() LIMIT 20;")
	List<Heatline> getNewsByType(@Param("category") String category);
}
