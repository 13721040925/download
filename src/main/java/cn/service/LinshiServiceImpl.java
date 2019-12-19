package cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.LinshiMapper;
import cn.pojo.Linshi;

@Service("linshiService")
public class LinshiServiceImpl implements LinshiService {
	@Resource
	private LinshiMapper mapper;
	@Override
	public int add(Linshi linshi) {
		// TODO Auto-generated method stub
		return mapper.add(linshi);
	}

}
