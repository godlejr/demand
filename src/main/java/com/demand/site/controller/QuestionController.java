package com.demand.site.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import com.demand.site.common.dto.ErrorMessage;
import com.demand.site.common.entity.Answer;
import com.demand.site.common.entity.Question;
import com.demand.site.common.entity.QuestionCategory;
import com.demand.site.common.entity.User;
import com.demand.site.common.flag.PaginationFlag;
import com.demand.site.service.QuestionCategoryService;
import com.demand.site.service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionCategoryService questionCategoryService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String question(Model model) throws Exception {

		List<QuestionCategory> questionCategories = questionCategoryService.getQuestionCategories();
		Question question = new Question();

		model.addAttribute("questionCategories", questionCategories);
		model.addAttribute("question", question);

		return "question/new";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Object question(@ModelAttribute @Valid Question question, BindingResult bindingResult, Model model)
			throws Exception {

		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			String message = fieldError.getDefaultMessage();

			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setMessage(message);

			return errorMessage;
		}

		long questionCategoryId = question.getQuestionCategoryId();
		questionService.saveQuestion(question, questionCategoryId);

		return true;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable long id,
			@PageableDefault(size = 15, sort = "sort", direction = Direction.DESC) Pageable pageable,
			HttpSession httpSession, Model model) throws Exception {
		Question question = questionService.getQuestionById(id);
		long questionCategoryId = question.getQuestionCategory().getId();

		Page<Question> questionPage = questionService.getQuestionsByQuestionCategoryIdAndPageable(questionCategoryId,
				pageable);
		int currentPageNo = questionPage.getNumber();
		int totalPageNo = questionPage.getTotalPages();
		int startPageNo = ((currentPageNo) / PaginationFlag.PAGE_VIEW_SIZE) * PaginationFlag.PAGE_VIEW_SIZE + 1;

		int endPageNo = startPageNo + PaginationFlag.PAGE_VIEW_SIZE - 1;

		if (endPageNo > totalPageNo) {
			endPageNo = totalPageNo;
		}

		Answer answer = new Answer();
		model.addAttribute("answer", answer);
		model.addAttribute("question", question);
		model.addAttribute("questionPage", questionPage);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		return "question/detail";
	}

	@RequestMapping(value = "/{id}/unlock", method = RequestMethod.POST)
	@ResponseBody
	public Question unlockQuestion(@PathVariable long id, @RequestParam("password") String password) throws Exception {
		return questionService.getCheckedQuestionByIdAndPassword(id, password);
	}
}
