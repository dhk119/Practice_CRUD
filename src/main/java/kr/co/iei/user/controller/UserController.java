package kr.co.iei.user.controller;



import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import kr.co.iei.user.model.dto.UserDTO;
import kr.co.iei.user.model.service.UserService;
import kr.co.iei.util.EmailSender;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private EmailSender emailSender;
	
	@GetMapping(value="/logInFrm")
	private String loginFrm() {
		return "/user/logInFrm";
	}//로그인 페이지 이동
	
	@PostMapping(value="/logIn")
	private String logIn(UserDTO u, HttpSession session) {
		UserDTO user = userService.selectOneMember(u);
		if(user != null) {
			session.setAttribute("user", user);
		}
		return "redirect:/";
	}//로그인
	
	@GetMapping(value="/logOut")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}//로그아웃
	
	@GetMapping(value="/myPage")
	public String myPage() {
		return "user/myPage";
	}//마이 페이지 이동
	
	@PostMapping(value="/updateUser")
	public String update(UserDTO u, @SessionAttribute UserDTO user) {
		int result = userService.updateUser(u);
		if(result > 0) {
			user.setUserName(u.getUserName());
			user.setUserPhone(u.getUserPhone());
			user.setUserAddr(u.getUserAddr());
			return "redirect:/user/mypage";
		}else {
			return "redirect:/";
		}
	}//회원 정보 수정
	
	@GetMapping(value="/joinFrm")
	private String joinFrm() {
		return "/user/joinFrm";
	}//회원가입 페이지 이동
	
	@PostMapping(value="/join")
	public String join(UserDTO user) {
		int result = userService.registUser(user);
		if(result > 0) {
			return"redirect:/";
		}else {
			return "redirect:/user/joinFrm";
		}
	}//회원등록
	
	@ResponseBody
	@GetMapping(value="/checkId")
	public int checkId(UserDTO u) {
		UserDTO user = userService.checkId(u);
		if(user != null) {
			return 1; //이미 사용중인 아이디 일 경우
		}else {
			return 0; // 사용 가능한 아이디 일 경우
		}
	}//아이디 중복 체크
	
	@GetMapping(value="/updatePwFrm")
	public String updatePwFrm() {
		return "/user/updatePwFrm";
	}//비밀번호 변경하기
	
	@ResponseBody
	@PostMapping(value="/sendCode")
	public String sendCode(String receiver) {
		String emailTitle = "PRACTICE CRUD 인증메일";
		Random r = new Random();
		StringBuffer sb = new StringBuffer(); // 기존 문자 변경 함수
		for(int i=0;i<6;i++) {
			int flag = r.nextInt(3);
			if(flag == 0) {
				int randomCode = r.nextInt(10);
				sb.append(randomCode);
			}else if(flag == 1) {
				char randomCode = (char)(r.nextInt(26)+65); //문자로 형변환 후 보내주기
				sb.append(randomCode);
			}else if(flag == 2) {
				char randomCode = (char)(r.nextInt(26)+97);
				sb.append(randomCode);
			}
		}
		String emailContent = "<h1>안녕하세요. PRACTICE CRUD 입니다.</h1>"
				+"<h3>인증번호는 [<span style='color:red;'>"
				+sb.toString() // 정수타입을 문자타입으로 변형해서 보내기
				+"</span>]입니다.</h3>";
		emailSender.sendMail(emailTitle, receiver, emailContent);
		return sb.toString();
	}//이메일 인증 코드 보내기
	
	@ResponseBody
	@PostMapping(value="/oldPwCheck")
	public int oldPwCheck(UserDTO u) {
		UserDTO user = userService.selectOneMember(u);
		if(user != null) {
			return 1;
		}else {
			return 0;
		}
	}//기존 비밀번호 확인용
	
	@PostMapping(value="/changePw")
	public String changePw(UserDTO u) {
		int result = userService.updatePw(u);
		return "redirect:/";
	}//비밀번호 재설정
	
	@GetMapping(value="/findIdFrm")
	public String findIdFrm() {
		return "/user/findIdFrm";
	}//아이디 찾기 페이지 이동
	
	@PostMapping(value="/findId")
	public String findId(UserDTO u, Model model) {
		UserDTO user = userService.findId(u);
		if(user != null) {
			model.addAttribute("title", "아이디");
			model.addAttribute("msg", "아이디는 "+user.getUserId()+" 입니다.");
			model.addAttribute("icon","success");
			model.addAttribute("loc","/user/logInFrm");
			return "common/msg";
		}else {
			return null;
		}
	}//아이디 찾기
	
	@GetMapping(value="/findPw")
	public String findPw() {
		return "/user/findPw";
	}//비밀번호 찾은 후 재설정
	
	@GetMapping(value="/delete")
	public String delete(@SessionAttribute UserDTO user) {
		int userNo = user.getUserNo();
		int result = userService.userDelete(userNo);
		return "redirect:/";
	}
}
