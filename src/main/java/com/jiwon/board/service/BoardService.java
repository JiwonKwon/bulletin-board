package com.jiwon.board.service;

import com.jiwon.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    Page<Board> findBoardList(Pageable pageable);

    Board findBoardByIndex(long index);

}
