package me.wane.boardwithvuespring.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.wane.boardwithvuespring.adapter.out.mapper.BoardMapper;
import me.wane.boardwithvuespring.application.port.out.BoardPersistencePort;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class BoardPersistenceJpaAdapter implements BoardPersistencePort {
    private final BoardMapper boardMapper;
    private final BoardEntityRepository boardEntityRepository;

    private static final Sort SORT_ID_DESC = Sort.by(Sort.Direction.DESC, "id");


    /* Hibernate: SELECT * FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(? IN BOOLEAN MODE) ORDER BY b.id DESC */
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

    /**
     * Hibernate: SELECT * FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(? IN BOOLEAN MODE) ORDER BY b.id DESC limit ?
     * Hibernate: SELECT COUNT(*) FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(? IN BOOLEAN MODE)
     * <p>
     * WARN ration$PageModule$WarningLoggingModifier :
     * Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
     * For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
     * or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
     */
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

    /*  select be1_0.id,be1_0.content,be1_0.title from board_entity be1_0 order by be1_0.id desc limit ?,? */
    @Override
    public Window<Board> getBoardWindow(int offset, int size) {

        PageRequest pageRequest = PageRequest.ofSize(size);
        OffsetScrollPosition position;

        if (offset == -1) {
            position = ScrollPosition.offset();
        } else {
            position = ScrollPosition.offset(offset);
        }

        Window<BoardEntity> boardEntityWindow = boardEntityRepository.findByOrderByIdDesc(position, pageRequest);

        return boardEntityWindow.map(boardMapper::toDomain);
    }

    /* select be1_0.id,be1_0.content,be1_0.title from board_entity be1_0 where be1_0.id<? order by be1_0.id desc limit ? */
    /* SELECT * FROM board_entity b WHERE MATCH(b.title, b.content) AGAINST(? IN BOOLEAN MODE) AND b.id < ? order by b.id desc limit ? */
    @Override
    public List<Board> getBoardListByCursor(long lastId, int size, String searchKeyword) {

        log.info("lastId: {}, size: {}, searchKeyword: {}", lastId, size, searchKeyword);

        PageRequest pageRequest = PageRequest.of(0, size, SORT_ID_DESC);
        List<BoardEntity> boardEntities;

        if (lastId == -1) {
            lastId = Long.MAX_VALUE;
        }

        if (searchKeyword.isEmpty()) {
            boardEntities = boardEntityRepository.findCursorBy(lastId, pageRequest);
        } else {
            boardEntities = boardEntityRepository.findCursorByTitleOrContentMatchAgainst(lastId, searchKeyword, pageRequest);
        }

        return boardEntities.stream().map(boardMapper::toDomain).toList();
    }
}
