package com.demand.site.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demand.site.common.annotation.EmployeeRequired;
import com.demand.site.common.dto.ErrorMessage;
import com.demand.site.common.entity.Answer;
import com.demand.site.common.entity.Question;
import com.demand.site.common.entity.QuestionAnswer;
import com.demand.site.common.entity.User;
import com.demand.site.service.AnswerService;

@Controller
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@EmployeeRequired
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Object question(@ModelAttribute @Valid Answer answer, BindingResult bindingResult, Model model,
			HttpSession httpSession) throws Exception {

		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			String message = fieldError.getDefaultMessage();

			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setMessage(message);

			return errorMessage;
		}

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			answerService.saveAnswer(answer, user);
		}

		return true;
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public Object edit(@PathVariable("id") long id, Model model, HttpSession httpSession) throws Exception {
		Answer answer = answerService.getAnswerById(id);
		QuestionAnswer questionAnswer = answer.getQuestionAnswer();
		Question question = questionAnswer.getQuestion();

		model.addAttribute("answer", answer);
		model.addAttribute("question", question);

		return "answer/edit";
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object edit(@ModelAttribute @Valid Answer answer, BindingResult bindingResult, Model model,
			HttpSession httpSession) throws Exception {

		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			String message = fieldError.getDefaultMessage();

			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setMessage(message);

			return errorMessage;
		}

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			answerService.updateAnswer(answer, user);
		}

		return true;
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("id") long id, @RequestParam long questionId, HttpSession httpSession,
			Model model) throws Exception {

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			answerService.deleteAnswerById(id);
		}
		return "redirect:/questions/" + questionId;
	}

}
