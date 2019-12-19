package cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.pojo.Driving;

public interface DrivingMapper {
	@Insert(" insert into driving (question,answer,item1,item2,item3,item4,explains,url,subject,model) values (#{question},#{answer},#{item1},#{item2},#{item3},#{item4},#{explains},#{url},#{subject},#{model}) ")
	int addDriving(Driving driving);

	@Select(" select * from driving where model=#{model} ORDER BY RAND() LIMIT 100;")
	List<Driving> getDrivingQuestions(@Param("model") String model);
}
