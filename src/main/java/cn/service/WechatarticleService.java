package cn.service;

import java.util.List;

import cn.pojo.Wechatarticle;

public interface WechatarticleService {
	int addWechatarticle(Wechatarticle wechatarticle);

	List<Wechatarticle> getWechatNews();
}
