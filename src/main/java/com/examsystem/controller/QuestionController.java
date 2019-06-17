package com.examsystem.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examsystem.model.QuestionResponse;
import com.examsystem.model.QuestionView;
import com.examsystem.model.User;
import com.examsystem.service.QuestionService;


@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value= {"/list"},method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
	public @ResponseBody ResponseEntity<?>  listQuestion(){
		
		QuestionResponse questions = questionService.getQuestions();
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}
	
	
	@RequestMapping(value= {"/add"},method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?>  addQuestion(HttpServletRequest request, @RequestBody QuestionView questionView) {
		
		
		User user = (User) request.getSession().getAttribute("user");
		if(user==null || user.getAccess().equals("STUDENT"))
			return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
		
		try {
		  questionService.addQuestion(questionView);
		}catch(Exception e) {
			return new ResponseEntity<>("Insertion error",HttpStatus.EXPECTATION_FAILED); 
		}
		return new ResponseEntity<>("Successfully inserted",HttpStatus.OK);
		
	}
	
	@RequestMapping(value= {"/remove"},method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?>  removeQuestion(HttpServletRequest request, @RequestBody int id){
		
		User user = (User) request.getSession().getAttribute("user");
		if(user==null || user.getAccess().equals("STUDENT"))
			return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
		
		try {
			questionService.deleteQuestion(id);
		}catch(Exception e) {
			return new ResponseEntity<>("Delete Failed",HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("Delete Successful",HttpStatus.OK);
		
	}
	
	
	
	

}
