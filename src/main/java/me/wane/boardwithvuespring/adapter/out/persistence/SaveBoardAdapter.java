package me.wane.boardwithvuespring.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.wane.boardwithvuespring.adapter.out.mapper.BoardMapper;
import me.wane.boardwithvuespring.application.port.out.SaveBoardPort;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Repository
public class SaveBoardAdapter implements SaveBoardPort {

    private final BoardMapper boardMapper;
    private final BoardEntityRepository boardEntityRepository;

    @Override
    public Board execute(Board board) {
        BoardEntity entity = boardMapper.toEntity(board);
        boardEntityRepository.save(entity);

        return boardMapper.toDomain(entity);
    }
}
