package kr.co.iei.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.user.model.dao.UserDao;
import kr.co.iei.user.model.dto.UserDTO;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public int registUser(UserDTO user) {
		//db에 insert하기 전에 pw 암호화
		String defaultPw = user.getUserPw();//사용자가 입력한 pw
		String encPw = encoder.encode(defaultPw);//암호화 된 pw
		user.setUserPw(encPw); //암호화된 비밀번호를 사용자 객체에 저장
		int result = userDao.registUser(user);
		return result;
	}//회원가입

	public UserDTO checkId(UserDTO u) {
		UserDTO user = userDao.selectOneUser(u);
		return user;
	}//아이디 체크용

	public UserDTO selectOneMember(UserDTO u) {
		UserDTO user = userDao.selectOneUser(u);
		if(user != null) {
			if(encoder.matches(u.getUserPw(), user.getUserPw())) {
				user.setUserPw(null);
				return user;
			}else {
				return null;
			}
		}else {
			return null;			
		}
	}//로그인 할때
	
	@Transactional
	public int updateUser(UserDTO u) {
//		String defaultPw = u.getUserPw();
//		String encPw = encoder.encode(defaultPw);
//		u.setUserPw(encPw);
		System.out.println(u);
		int result = userDao.updateUser(u);
		return result;
	}//회원 정보 수정
	@Transactional
	public int updatePw(UserDTO u) {
		String encPw = encoder.encode(u.getUserPw());
		u.setUserPw(encPw);
		int result = userDao.changePw(u);
		return result;
	}

	@Transactional
	public int userDelete(int userNo) {
		int result = userDao.userDelete(userNo);
		return result;
	}

	public UserDTO findId(UserDTO u) {
		UserDTO user = userDao.findOneUser(u);
		return user;
	}
}
