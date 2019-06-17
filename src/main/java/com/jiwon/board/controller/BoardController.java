package com.jiwon.board.controller;

import com.jiwon.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "index", defaultValue = "0") long index,
                        Model model) {
        model.addAttribute("board", boardService.findBoardByIndex(index));
        return "/board/form";
    }

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "/board/list";
    }

    /*@RequestMapping(value="/board/post", method=RequestMethod.POST)
    public int insertBoard(Board board, Model model) {
        return 0;
    }

    @RequestMapping(value="/board/board", method=RequestMethod.GET)
    public Board selectBoard(Board board, Model model) {
        return null;
    }

    @RequestMapping(value="/board/board", method=RequestMethod.PUT)
    public int updateBoard(Board board, Model model) {
        return 0;
    }

    @RequestMapping(value="/board/board", method=RequestMethod.DELETE)
    public int deleteBoard(int boardNo) {
        return 0;
    }*/

}
