package cn.pojo;

import org.springframework.stereotype.Component;

@Component("driving")
public class Driving {
	private Long id;
	private String question;
	private String answer;
	private String item1;
	private String item2;
	private String item3;
	private String item4;
	private String explains;
	private String url;
	private String subject;
	private String model;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}

	public String getItem3() {
		return item3;
	}

	public void setItem3(String item3) {
		this.item3 = item3;
	}

	public String getItem4() {
		return item4;
	}

	public void setItem4(String item4) {
		this.item4 = item4;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Driving [id=" + id + ", question=" + question + ", answer=" + answer + ", item1=" + item1 + ", item2="
				+ item2 + ", item3=" + item3 + ", item4=" + item4 + ", explains=" + explains + ", url=" + url
				+ ", subject=" + subject + ", model=" + model + "]";
	}

}
