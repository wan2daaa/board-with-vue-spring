package me.wane.boardwithvuespring.application.service;

import lombok.RequiredArgsConstructor;
import me.wane.boardwithvuespring.adapter.in.web.dto.CreateBoardRequest;
import me.wane.boardwithvuespring.application.port.in.CreateBoardUseCase;
import me.wane.boardwithvuespring.application.port.out.SaveBoardPort;
import me.wane.boardwithvuespring.domain.Board;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateBoardService implements CreateBoardUseCase {

    private final SaveBoardPort saveBoardPort;

    @Override
    public Board execute(CreateBoardRequest request) {
        Board board = Board.create(request.getTitle(), request.getContent());

        return saveBoardPort.execute(board);
    }
}
