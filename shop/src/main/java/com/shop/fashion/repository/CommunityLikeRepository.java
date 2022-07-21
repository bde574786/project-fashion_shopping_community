package com.shop.fashion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.fashion.model.CommunityLike;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Integer> {

	Optional<CommunityLike> findByBoardIdAndUserId(int boardId, int userId);
}
