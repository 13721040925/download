package cn.mapper;

import org.apache.ibatis.annotations.Insert;

import cn.pojo.Linshi;

public interface LinshiMapper {
	@Insert(" insert into linshi (details) values (details) ")
	int add(Linshi linshi);
}
