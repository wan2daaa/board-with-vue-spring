package me.wane.boardwithvuespring.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.wane.boardwithvuespring.adapter.out.mapper.BoardMapper;
import me.wane.boardwithvuespring.application.port.out.BoardPersistencePort;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistenceJpaAdapter implements BoardPersistencePort {
    private final BoardMapper boardMapper;
    private final BoardEntityRepository boardEntityRepository;

    @Override
    public List<Board> getBoardListIdDesc() {
        List<BoardEntity> boardEntities = boardEntityRepository.findAllByOrderByIdDesc();

        return boardEntities.stream().map(boardMapper::toDomain).toList();
    }

    @Override
    public List<Board> getBoardListBySearchKeyword(String searchKeyword) {
        List<BoardEntity> boardEntities = boardEntityRepository.findAllByTitleOrContentMatchAgainstOrderByIdDesc(searchKeyword);

        return boardEntities.stream().map(boardMapper::toDomain).toList();
    }
}
