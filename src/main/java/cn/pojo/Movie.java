package cn.pojo;

import org.springframework.stereotype.Component;

@Component("movie")
public class Movie {
	private Integer id;
	private String name;
	private String picpath;
	private String moviepath;
	private String detail;
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public String getMoviepath() {
		return moviepath;
	}

	public void setMoviepath(String moviepath) {
		this.moviepath = moviepath;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", picpath=" + picpath + ", moviepath=" + moviepath + ", detail="
				+ detail + ", type=" + type + "]";
	}

}
