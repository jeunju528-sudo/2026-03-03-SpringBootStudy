package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
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
@Table(name="jpaboard")
@SequenceGenerator(name="jpb_no_seq", sequenceName = "jpb_no_seq", allocationSize = 1)
@DynamicUpdate
@Data
public class BoardEntity {
	@Id // primary key, 자동 증가 컬럼
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpb_no_seq") // @SequenceGenerator(name="jpb_no_seq") 여기 name값 작성
	private int no;
	private String name, subject, content;
	@Column(insertable = true, updatable = false) // update는 못하게 설정
	private String pwd;
	@ColumnDefault("0")
	private int hit;
	@Column(insertable = true, updatable = false) // update는 못하게 설정
	@ColumnDefault("SYSDATE")
	private String regdate;
	
	@PrePersist
	public void regdate() {
		this.regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
