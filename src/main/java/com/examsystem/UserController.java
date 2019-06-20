package com.examsystem;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examsystem.model.LoginUtil;
import com.examsystem.model.SessionQuestionUtil;
import com.examsystem.model.User;
import com.examsystem.model.UserView;
import com.examsystem.service.LoginService;
import com.examsystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController  {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value= {"/solve"},method = RequestMethod.GET)
	public ResponseEntity<?> solve(HttpServletRequest request){
		try {
		request.getSession().setAttribute("questions", userService.solveQuestions());
		System.out.println(request.getSession().getAttribute("questions"));
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception Occured!",HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("Session Updated",HttpStatus.OK);
	}
	@RequestMapping(value= {"/login"},method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> login(HttpServletRequest request, @RequestBody LoginUtil credentials) {
		String regNo = credentials.getRegno();
		String pass = credentials.getPassword();
		boolean result = loginService.authenticateUser(regNo,pass);
		User user = userService.loadUserByRegno(regNo);
		
		if(result==true) {
			request.getSession().setAttribute("user",user);
			return new ResponseEntity<>("Login Success",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Login Failed",HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value= {"/register"},method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> register(@RequestBody UserView userView) {
		if(StringUtils.isBlank(userView.getRegno()) || StringUtils.isBlank(userView.getFirstname()))
			return new ResponseEntity<>("Registration Failed",HttpStatus.EXPECTATION_FAILED);
		userView.setAccess("STUDENT");
		try {
			userService.addUser(userView);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Registration Failed",HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("Registration Success",HttpStatus.OK);
		
	}
	
	@RequestMapping(value= {"/logout"},method = RequestMethod.GET)
	public ResponseEntity<?> logout(HttpServletRequest request) {
		try {
			request.getSession().invalidate();
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Logout Failed",HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("Logout Successful",HttpStatus.OK);
	}
	
	@RequestMapping(value= {"/save"},method= RequestMethod.GET)
	public ResponseEntity<?> save(HttpServletRequest request){
		
		List<SessionQuestionUtil>retrievedQuestions = new ArrayList<SessionQuestionUtil>();
		retrievedQuestions = (List<SessionQuestionUtil>) request.getSession().getAttribute("questions");
		int uid = ((User) request.getSession().getAttribute("user")).getId();
		try {
			userService.finalSubmit(retrievedQuestions,uid);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Submit Failed!", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("Submitted!",HttpStatus.OK);
		
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value= {"/submit"}, method= RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestParam(value="id") int qno, @RequestBody String answer, HttpServletRequest request) {
		
		try {
		List<SessionQuestionUtil>questionOb = new ArrayList<SessionQuestionUtil>();
		
		questionOb =  (List<SessionQuestionUtil>) request.getSession().getAttribute("questions");
		
		for(int i=0; i<questionOb.size(); i++) {  //updating session variable
			if(questionOb.get(i).getNewId()==qno) {
				questionOb.get(i).setSubmittedAnswer(answer);
					break;
			}
		}
		request.getSession().setAttribute("questions", questionOb);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception Occurred",HttpStatus.EXPECTATION_FAILED);
		}
		System.out.println(request.getSession().getAttribute("questions"));
		return new ResponseEntity<>("Session updated",HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value= {"/calculateresult"}, method=RequestMethod.GET)
	public ResponseEntity<?> result(HttpServletRequest request){
		
		User user = (User) request.getSession().getAttribute("user");
		if(user==null || user.getAccess().equals("STUDENT"))
			return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
		
		try {
		userService.calculateresult();
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("calculation failed",HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("Results table updated",HttpStatus.OK);
	}
}

	

