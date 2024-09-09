package kr.co.iei.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.board.model.service.BoardService;

@Controller
@RequestMapping (value="/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/boardList")
	public String boardList() {
		return "/board/boardList";
	}//게시판
	
	@GetMapping(value="/view")
	public String boardView() {
		return "/board/view";
	}//게시판 상세보기
	
	@GetMapping(value="/boardFrm")
	public String boardFrm() {
		return "/board/boardFrm";
	}//글쓰기
}
