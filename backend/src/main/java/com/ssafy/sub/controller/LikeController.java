package com.ssafy.sub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sub.model.response.ResponseMessage;
import com.ssafy.sub.model.response.Result;
import com.ssafy.sub.model.response.StatusCode;
import com.ssafy.sub.service.LikeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/likes")
public class LikeController {

	@Autowired
	private LikeService likeService;

	// 1. feed like
	@ApiOperation(value = "피드 좋아요", response = Result.class)
	@PostMapping(value = "/feed/{fid}")
	public ResponseEntity feedLikeInsert(@PathVariable int fid, Authentication authentication) {
		int uid = Integer.parseInt(authentication.getName());
		likeService.feedLikeInsert(fid, uid);
		
		Result result = new Result(StatusCode.OK, ResponseMessage.LIKE_FEED, null);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	// 2. feed unlike
	@ApiOperation(value = "피드 좋아요 취소", response = Result.class)
	@DeleteMapping(value = "/feed/{fid}")
	public ResponseEntity feedLikeDelete(@PathVariable int fid, Authentication authentication) {
		int uid = Integer.parseInt(authentication.getName());
		likeService.feedLikeDelete(fid, uid);
		
		Result result = new Result(StatusCode.OK, ResponseMessage.UNLIKE_FEED, null);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	// 3. comment like
	@ApiOperation(value = "댓글 좋아요", response = Result.class)
	@PostMapping(value = "/comment/{cid}")
	public ResponseEntity commentLikeInsert(@PathVariable int cid, Authentication authentication) {
		int uid = Integer.parseInt(authentication.getName());
		likeService.commentLikeInsert(cid, uid);
		
		Result result = new Result(StatusCode.OK, ResponseMessage.LIKE_COMMENT, null);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	// 4. comment unlike
	@ApiOperation(value = "댓글 좋아요 취소", response = Result.class)
	@DeleteMapping(value = "/comment/{cid}")
	public ResponseEntity commentLikeDelete(@PathVariable int cid, Authentication authentication) {
		int uid = Integer.parseInt(authentication.getName());
		likeService.commentLikeDelete(cid, uid);
		
		Result result = new Result(StatusCode.OK, ResponseMessage.UNLIKE_COMMENT, null);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
}