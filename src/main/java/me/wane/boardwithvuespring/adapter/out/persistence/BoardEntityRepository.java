package me.wane.boardwithvuespring.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(:keyword IN BOOLEAN MODE) ORDER BY b.id DESC",
            nativeQuery = true)
    List<BoardEntity> findAllByTitleOrContentMatchAgainstOrderByIdDesc(@Param("keyword") String searchKeyword);
}