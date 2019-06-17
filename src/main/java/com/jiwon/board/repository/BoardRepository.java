package com.jiwon.board.repository;

import com.jiwon.board.domain.Board;
import com.jiwon.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
