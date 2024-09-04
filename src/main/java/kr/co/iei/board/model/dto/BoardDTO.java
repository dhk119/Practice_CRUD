package kr.co.iei.board.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value="board")
public class BoardDTO {
	private int boardNo;
	private int userNo;
	private String boardTitle;
	private String boardContent;
	private String boardImg;
	private String regDate;
	private String boardWriter;
	private int boardLike;
	private int readCount;
	private String boardCategory;
}
