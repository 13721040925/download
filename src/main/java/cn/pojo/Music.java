package cn.pojo;

import org.springframework.stereotype.Component;

@Component("music")
public class Music {
	private String singername;
	private String m4a;
	private String pic;
	private String songname;
	private String lrc;
	private String mp3;

	public String getSingername() {
		return singername;
	}

	public void setSingername(String singername) {
		this.singername = singername;
	}

	public String getM4a() {
		return m4a;
	}

	public void setM4a(String m4a) {
		this.m4a = m4a;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public String getLrc() {
		return lrc;
	}

	public void setLrc(String lrc) {
		this.lrc = lrc;
	}

	public String getMp3() {
		return mp3;
	}

	public void setMp3(String mp3) {
		this.mp3 = mp3;
	}

	@Override
	public String toString() {
		return "Music [singername=" + singername + ", m4a=" + m4a + ", pic=" + pic + ", songname=" + songname + ", lrc="
				+ lrc + ", mp3=" + mp3 + "]";
	}


}
