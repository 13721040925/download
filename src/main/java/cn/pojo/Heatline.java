package cn.pojo;

import org.springframework.stereotype.Component;

@Component("heatline")
public class Heatline {
	private Long id;
	private String title;
	private String category;
	private String author_name;
	private String url;
	private String thumbnail_pic_s;
	private String thumbnail_pic_s02;
	private String thumbnail_pic_s03;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnail_pic_s() {
		return thumbnail_pic_s;
	}

	public void setThumbnail_pic_s(String thumbnail_pic_s) {
		this.thumbnail_pic_s = thumbnail_pic_s;
	}

	public String getThumbnail_pic_s02() {
		return thumbnail_pic_s02;
	}

	public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
		this.thumbnail_pic_s02 = thumbnail_pic_s02;
	}

	public String getThumbnail_pic_s03() {
		return thumbnail_pic_s03;
	}

	public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
		this.thumbnail_pic_s03 = thumbnail_pic_s03;
	}

	@Override
	public String toString() {
		return "Heatline [id=" + id + ", title=" + title + ", category=" + category + ", author_name=" + author_name
				+ ", url=" + url + ", thumbnail_pic_s=" + thumbnail_pic_s + ", thumbnail_pic_s02=" + thumbnail_pic_s02
				+ ", thumbnail_pic_s03=" + thumbnail_pic_s03 + "]";
	}

}
