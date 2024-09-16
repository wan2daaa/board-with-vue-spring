package me.wane.boardwithvuespring.adapter.out.mapper;

import me.wane.boardwithvuespring.adapter.out.persistence.BoardEntity;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public BoardEntity toEntity(Board board) {
        return new BoardEntity(
                board.getId(),
                board.getTitle(),
                board.getContent()
        );
    }

    public Board toDomain(BoardEntity boardEntity) {
        return new Board(
                boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getContent()
        );
    }
}
