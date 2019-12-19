package cn.service;

import java.util.List;

import cn.pojo.Heatline;

public interface HeatlineService {
	int addHeatline(Heatline heatline);

	List<Heatline> getNewsByType(String category);
}
