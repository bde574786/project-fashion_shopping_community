package com.shop.fashion.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.fashion.dto.CommunityDto;
import com.shop.fashion.model.CommunityBoard;
import com.shop.fashion.model.User;
import com.shop.fashion.repository.CommunityRepository;

@Service
public class CommunityService {
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Autowired
	private CommunityRepository communityRepository;
	
	public List<CommunityBoard> getCommunityBoardList() {
		return communityRepository.findAll();
	}

	@Transactional
	public void upload(CommunityDto fileDto, User user) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid.toString() +"." + extracktExt(fileDto.getFile().getOriginalFilename());
		
		String newFileName = (imageFileName.trim()).replaceAll("\\s", "");
		
		Path imageFilePath = Paths.get(uploadFolder + newFileName);
		
		try {
			Files.write(imageFilePath, fileDto.getFile().getBytes());
			
			CommunityBoard communityBoardEntity = fileDto.toEntity(newFileName, user);
			communityRepository.save(communityBoardEntity);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String extracktExt(String originalFileName) {
		int pos = originalFileName.lastIndexOf(".");
		return originalFileName.substring(pos + 1);
	}
	
		
	
	@Transactional
	public CommunityBoard boardDetail(int id) {
		return communityRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글은 찾을 수 없습니다");
		});
	}
	
	
	@Transactional
	public void modifyBoard(int id, CommunityBoard board) { // title, content
		CommunityBoard boardEntity = communityRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 글은 찾을 수 없습니다");
		});
		
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		boardEntity.setUser(board.getUser());
		boardEntity.setImageUrl(board.getImageUrl());
	}
	
	
	@Transactional
	public void deleteById(int id) {
		communityRepository.deleteById(id);
	}
	

	@Autowired
	private CommunityReplyRepository communityReplyRepository;
	@Autowired
	private CommunityRepository communityRepository;
	@Autowired
	private CommunityLikeRepository communityLikeRepository;
	
	// 상세보기 화면 게시물
	@Transactional
	public CommunityBoard detailCommunityBoard(int id) {
		return communityRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("게시물이 존재하지 않습니다.");
		});
	}
	
	// 좋아요 화면에 랜더링 하기 위한 확인 작업 
	@Transactional
	public CommunityLike isLike(int boardId, int userId) {
		return communityLikeRepository.findByBoardIdAndUserId(boardId, userId).orElseGet(() -> {
			return new CommunityLike();
		});
	}

	// 댓글 처리하기 전에 보드가 있나 확인
	@Transactional
	public CommunityBoard checkBoard(int id) {
		return communityRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("게시물이 존재하지 않아 댓글쓰기에 실패하였습니다.");
		});
	}

	// 댓글 쓰기
	@Transactional
	public Reply insertReply(int boardId, Reply reply, User user) {
		System.out.println("서비스단에  : " + boardId);
		// 댓글쓰는 중에 게시물 삭제했는지 체크 
		CommunityBoard board = checkBoard(boardId);
		reply.setUser(user);
		reply.setBoard(board);
		System.out.println("게시물 존재하고 넘어감");
		return communityReplyRepository.save(reply);
	}
	
	// 댓글 삭제
	@Transactional
	public void deleteReply(int id) {
		communityReplyRepository.deleteById(id);
	}
	
	// 댓글 수정
	@Transactional
	public Reply updateReply(Reply reply) {
		Reply replyEntity = communityReplyRepository.findById(reply.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("게시물이 존재하지 않아 댓글수정에 실패하였습니다.");
		});
		replyEntity.setContent(reply.getContent());
		return replyEntity;
	}
	
	// 좋아요 
	@Transactional
	public CommunityLike checkLike(int communityBoardId, User user) {
		CommunityBoard board = checkBoard(communityBoardId);

		CommunityLike like =  communityLikeRepository.findByBoardIdAndUserId(communityBoardId, user.getId()).orElseGet(() -> {
			return new CommunityLike();
		});
		
		// 좋아요를 취소하는 상황
		if(like.getIsLike() == 1) {
			board.setLikeCount(board.getLikeCount() - 1);
			deleteLike(like.getId());
			return null;
		// 좋아요를 누르는 상황
		} else {
			board.setLikeCount(board.getLikeCount() + 1);
			like.setBoard(board);
			like.setUser(user);
			like.setIsLike(1);
			communityLikeRepository.save(like);
			return like;
		}
	}
	
	@Transactional
	public void deleteLike(int likeId) {
		communityLikeRepository.deleteById(likeId);
	}

}
