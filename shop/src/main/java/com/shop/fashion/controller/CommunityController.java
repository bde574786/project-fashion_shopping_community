package com.shop.fashion.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.fashion.auth.PrincipalUserDetail;
import com.shop.fashion.dto.CommunityDto;
import com.shop.fashion.model.CommunityBoard;
import com.shop.fashion.service.CommunityService;

@Controller
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<CommunityBoard> boardList = communityService.getCommunityBoardList();
		Collections.reverse(boardList);
		System.out.println(boardList.toString());
		model.addAttribute("boardList", boardList);
		return "index";
	}
	
	@GetMapping("/board/write")
	public String write() {
		return "community/write_form";
	}
	
	@PostMapping("/board/upload")
	public String storyUpload(CommunityDto fileDto, @AuthenticationPrincipal PrincipalUserDetail detail) {
		communityService.upload(fileDto, detail.getUser());
		return "redirect:/";
	}
	

}
