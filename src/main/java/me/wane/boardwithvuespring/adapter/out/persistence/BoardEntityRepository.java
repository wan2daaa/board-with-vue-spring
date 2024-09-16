package me.wane.boardwithvuespring.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findAllByOrderByIdDesc();
}