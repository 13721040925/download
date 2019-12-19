package cn.pojo;

import org.springframework.stereotype.Component;

@Component("linshi")
public class Linshi {
	private Long id;
	private String details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Linshi [id=" + id + ", details=" + details + ", getId()=" + getId() + ", getDetails()=" + getDetails()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
