package me.wane.boardwithvuespring.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.wane.boardwithvuespring.adapter.out.mapper.BoardMapper;
import me.wane.boardwithvuespring.application.port.out.BoardPersistencePort;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistenceJpaAdapter implements BoardPersistencePort {
    private final BoardMapper boardMapper;
    private final BoardEntityRepository boardEntityRepository;

    private static final Sort SORT_ID_DESC = Sort.by(Sort.Direction.DESC, "id");

    @Override
    public List<Board> getBoardListBySearchKeyword(String searchKeyword) {

        List<BoardEntity> boardEntities;

        if (searchKeyword.isEmpty()) {
            boardEntities = boardEntityRepository.findAll(SORT_ID_DESC);
        } else {
            boardEntities = boardEntityRepository.findAllByTitleOrContentMatchAgainstOrderByIdDesc(searchKeyword);
        }

        return boardEntities.stream().map(boardMapper::toDomain).toList();
    }

    @Override
    public Page<Board> getBoardPageBySearchKeyword(PageRequest pageRequest, String searchKeyword) {
        Page<BoardEntity> boardEntityPage;

        if (searchKeyword.isEmpty()) {
            PageRequest pageRequestWithSortIdDesc = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(), SORT_ID_DESC);
            boardEntityPage = boardEntityRepository.findAll(pageRequestWithSortIdDesc);
        } else {
            boardEntityPage = boardEntityRepository.findAllByTitleOrContentMatchAgainstOrderByIdDesc(searchKeyword, pageRequest);
        }

        return boardEntityPage.map(boardMapper::toDomain);
    }
}
