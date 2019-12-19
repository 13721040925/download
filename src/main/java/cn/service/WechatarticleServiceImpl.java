package cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.WechatarticleMapper;
import cn.pojo.Wechatarticle;

@Service("wechatarticleService")
public class WechatarticleServiceImpl implements WechatarticleService {
	@Resource
	private WechatarticleMapper mapper;
	@Override
	public int addWechatarticle(Wechatarticle wechatarticle) {
		// TODO Auto-generated method stub
		return mapper.addWechatarticle(wechatarticle);
	}

	@Override
	public List<Wechatarticle> getWechatNews() {
		// TODO Auto-generated method stub
		return mapper.getWechatNews();
	}

}
