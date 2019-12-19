package cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.pojo.Zongyi;

public interface ZongyiMapper {
	@Select(" select * from zongyi where typenum=#{typenum} ")
	List<Zongyi> selZongyis(@Param("typenum") Integer typenum);
}
