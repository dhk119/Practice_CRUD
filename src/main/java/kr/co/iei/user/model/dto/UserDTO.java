package kr.co.iei.user.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value="user")
public class UserDTO {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private String userBirth;
	private String userPhone;
	private String userGender;
	private String userAddr;
	private int userLevel;
	private String enrollDate;
}
