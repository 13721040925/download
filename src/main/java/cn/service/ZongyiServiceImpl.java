package cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.ZongyiMapper;
import cn.pojo.Zongyi;

@Service("zongyiService")
public class ZongyiServiceImpl implements ZongyiService {
	@Resource
	private ZongyiMapper mapper;
	@Override
	public List<Zongyi> selZongyis(Integer typenum) {
		// TODO Auto-generated method stub
		return mapper.selZongyis(typenum);
	}

}
