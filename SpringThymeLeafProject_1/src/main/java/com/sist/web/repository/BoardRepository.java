package com.sist.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sist.web.entity.BoardEntity;

/*
 * JpaRepository<T, ID>
 *  - T : 
 *  - ID : PK의 컬럼 타입 (Generic 형태로 작성)
 *  
 *  findByNo
 *        -- 조건절 컬럼명
 *      -- WHERE
 *  ---- SELECT
 *  
 *  findByNameLike
 *            ---- WHERE name LIKE
 *        ---- 조건절 컬럼명
 *  
 *  findByNoBetweenAnd
 *          ---------- WHERE no BETWEEN a AND b
 * */
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	@Query(value="SELECT * FROM board ORDER BY no DESC "
			+ "OFFSET :start ROWS FETCH NEXT 10 ROWS ONLY "
			, nativeQuery = true) // SQL을 JPQL로 변경없이 문장 그대로 수행
	public List<BoardEntity> boardListData(@Param("start") Integer start);
	
	public BoardEntity findByNo(int no);
	
	
}
