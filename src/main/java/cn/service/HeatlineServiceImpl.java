package cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.HeatlineMapper;
import cn.pojo.Heatline;

@Service("heatlineService")
public class HeatlineServiceImpl implements HeatlineService {
	@Resource
	private HeatlineMapper mapper;

	@Override
	public int addHeatline(Heatline heatline) {
		// TODO Auto-generated method stub
		return mapper.addHeatline(heatline);
	}

	@Override
	public List<Heatline> getNewsByType(String category) {
		// TODO Auto-generated method stub
		return mapper.getNewsByType(category);
	}

}
