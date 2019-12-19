package cn.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.pojo.Driving;
import cn.pojo.Heatline;
import cn.pojo.Linshi;
import cn.pojo.Wechatarticle;
import cn.service.DrivingService;
import cn.service.HeatlineService;
import cn.service.LinshiService;
import cn.service.WechatarticleService;

public class ToSqlUtil {
	private static ApplicationContext app = new ClassPathXmlApplicationContext("spring-config.xml");

	public static void HeatToSql(Heatline heatline) {
		HeatlineService heatlineService = app.getBean("heatlineService", HeatlineService.class);
		heatlineService.addHeatline(heatline);
	}

	public static void DrivingToSql(Driving driving) {
		DrivingService drivingService = app.getBean("drivingService", DrivingService.class);
		drivingService.addDriving(driving);
	}

	public static void AtarticleToSql(Wechatarticle wechatarticle) {
		WechatarticleService wechatarticleService = app.getBean("wechatarticleService", WechatarticleService.class);
		wechatarticleService.addWechatarticle(wechatarticle);
	}

	public static void LinshiToSql(Linshi linshi) {
		LinshiService linshiService = app.getBean("linshiService", LinshiService.class);
		linshiService.add(linshi);
	}
}
