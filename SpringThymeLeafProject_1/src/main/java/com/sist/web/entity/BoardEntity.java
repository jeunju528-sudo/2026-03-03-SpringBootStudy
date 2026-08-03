package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

/*
 * NO      NOT NULL NUMBER         
NAME    NOT NULL VARCHAR2(51)   
SUBJECT NOT NULL VARCHAR2(2000) 
CONTENT NOT NULL CLOB           
PWD     NOT NULL VARCHAR2(10)   
REGDATE          DATE           
HIT              NUMBER         
 * */
@Entity
@Table(name="board")
@DynamicUpdate
@Data
public class BoardEntity {
	@Id // primary key, 자동 증가 컬럼
	private int no;
	private String name, subject, content;
	@Column(insertable = true, updatable = false) // update는 못하게 설정
	private String pwd;
	private int hit;
	@Column(insertable = true, updatable = false) // update는 못하게 설정
	private String regdate;
	
	@PrePersist
	public void regdate() {
		this.regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
