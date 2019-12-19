package cn.service;

import java.util.List;

import cn.pojo.Driving;

public interface DrivingService {
	int addDriving(Driving driving);

	List<Driving> getDrivingQuestions(String model);
}
