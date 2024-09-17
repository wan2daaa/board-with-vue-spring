package me.wane.boardwithvuespring.adapter.out.persistence;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long> {

    @Query(value = "SELECT * FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(:keyword IN BOOLEAN MODE) ORDER BY b.id DESC",
            nativeQuery = true)
    List<BoardEntity> findAllByTitleOrContentMatchAgainstOrderByIdDesc(@Param("keyword") String searchKeyword);

    @Query(value = "SELECT * FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(:keyword IN BOOLEAN MODE) ORDER BY b.id DESC",
            countQuery = "SELECT COUNT(*) FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(:keyword IN BOOLEAN MODE)",
            nativeQuery = true)
    Page<BoardEntity> findAllByTitleOrContentMatchAgainstOrderByIdDesc(@Param("keyword") String searchKeyword, Pageable pageable);

    /* Scrolling with String-based query methods is not yet supported. Scrolling is also not supported using stored @Procedure query methods. */
//    @Query(value = "SELECT * FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(:keyword IN BOOLEAN MODE) ORDER BY b.id DESC",
//            countQuery = "SELECT COUNT(*) FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(:keyword IN BOOLEAN MODE)",
//            nativeQuery = true)
//    Window<BoardEntity> findAllByTitleOrContentMatchAgainstOrderByIdDesc(@Param("keyword") String searchKeyword, OffsetScrollPosition position);

    Window<BoardEntity> findByOrderByIdDesc(OffsetScrollPosition position, Pageable pageable);

    @Query("SELECT b FROM BoardEntity b WHERE b.id < :lastId")
    List<BoardEntity> findCursorBy(long lastId, PageRequest pageRequest);

//    @Query(value = "SELECT * FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(:keyword IN BOOLEAN MODE) AND b.id < :lastId ORDER BY b.id DESC",
//            nativeQuery = true)
@Query(value = "SELECT * FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(:keyword IN BOOLEAN MODE) AND b.id < :lastId",
        nativeQuery = true)
    List<BoardEntity> findCursorByTitleOrContentMatchAgainst(long lastId, @Param("keyword") String searchKeyword, PageRequest pageRequest);

}