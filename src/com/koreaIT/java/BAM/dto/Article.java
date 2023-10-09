package com.koreaIT.java.BAM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	public int memberId;

	public Article(int id, String regDate, String updateDate, int memberId, String title, String body) {
		this(id, regDate, updateDate, memberId, title, body, 0);
	}

	public Article(int id, String regDate, String updateDate, int memberId, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}
}