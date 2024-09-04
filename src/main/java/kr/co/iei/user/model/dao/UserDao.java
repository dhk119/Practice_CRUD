package kr.co.iei.user.model.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.user.model.dto.UserDTO;

@Mapper
public interface UserDao {

	int registUser(UserDTO user);

	UserDTO selectOneUser(UserDTO u);

	int updateUser(UserDTO u);

	int changePw(UserDTO u);

	int userDelete(int userNo);

	UserDTO findOneUser(UserDTO u);


}
