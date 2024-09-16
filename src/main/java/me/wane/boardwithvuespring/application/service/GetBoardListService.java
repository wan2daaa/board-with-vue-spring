package me.wane.boardwithvuespring.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.wane.boardwithvuespring.application.port.in.GetBoardListUseCase;
import me.wane.boardwithvuespring.application.port.out.BoardPersistencePort;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetBoardListService implements GetBoardListUseCase {

    private final BoardPersistencePort boardPersistencePort;

    @Override
    public List<Board> execute(String searchKeyword) {

        log.info("searchKeyword: {}", searchKeyword);

        if (searchKeyword.isEmpty()) {
            return boardPersistencePort.getBoardListIdDesc();
        }

        return boardPersistencePort.getBoardListBySearchKeyword(searchKeyword);
    }
}
