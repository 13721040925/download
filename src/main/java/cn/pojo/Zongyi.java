package cn.pojo;

import org.springframework.stereotype.Component;

@Component("zongyi")
public class Zongyi {
	private Integer id;
	private String title;
	private String details;
	private String fengmian;
	private Integer typenum;
	private String typename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getFengmian() {
		return fengmian;
	}

	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}

	public Integer getTypenum() {
		return typenum;
	}

	public void setTypenum(Integer typenum) {
		this.typenum = typenum;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Override
	public String toString() {
		return "Zongyi [id=" + id + ", title=" + title + ", details=" + details + ", fengmian=" + fengmian
				+ ", typenum=" + typenum + ", typename=" + typename + "]";
	}

}
