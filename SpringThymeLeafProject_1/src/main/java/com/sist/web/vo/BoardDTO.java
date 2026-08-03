package com.sist.web.vo;
/*
 * public record BoardDTO => Spring AI에서 사용하는 읽기전용 클래스
 * 
 * 인터페이스 기반 프로젝션(Interface-based Projection) 기능을 사용
 * -> 데이터베이스에서 조회한 결과를 JPA가 실행 시점에 동적 프록시(Dynamic Proxy) 객체로 생성하여 Interface의 getter 메서드 매칭을 통해 값을 넣어줌
 * -> 쿼리의 컬럼명과 getter의 명칭이 맞아야 가져올 수 있음
 * */
public interface BoardDTO {
	public int getNo();
	public String getName();
	public String getSubject();
	public String getContent();
	public String getDbday();
	public int getHit();
}
