package cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.DrivingMapper;
import cn.pojo.Driving;

@Service("drivingService")
public class DrivingServiceImpl implements DrivingService {
	@Resource
	private DrivingMapper mapper;

	@Override
	public int addDriving(Driving driving) {
		// TODO Auto-generated method stub
		return mapper.addDriving(driving);
	}

	@Override
	public List<Driving> getDrivingQuestions(String model) {
		// TODO Auto-generated method stub
		return mapper.getDrivingQuestions(model);
	}

}
