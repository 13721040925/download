package cn.pojo;

import org.springframework.stereotype.Component;

@Component("wechatarticle")
public class Wechatarticle {
	private Long id;
	private String title;
	private String source;
	private String firstImg;
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFirstImg() {
		return firstImg;
	}

	public void setFirstImg(String firstImg) {
		this.firstImg = firstImg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Wechatarticle [id=" + id + ", title=" + title + ", source=" + source + ", firstImg=" + firstImg
				+ ", url=" + url + "]";
	}

}
