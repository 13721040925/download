package cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.pojo.Wechatarticle;

public interface WechatarticleMapper {

	@Insert(" insert into wechatarticle (title,source,firstImg,url) values (#{title},#{source},#{firstImg},#{url}) ")
	int addWechatarticle(Wechatarticle wechatarticle);

	@Select(" select * from wechatarticle ORDER BY RAND() LIMIT 20;")
	List<Wechatarticle> getWechatNews();
}
