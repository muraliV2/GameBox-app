package com.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Entity.games;
import com.project.Entity.review;
import com.project.Entity.user;
import com.project.dao.gamesdao;
import com.project.dao.reviewdao;
import com.project.dao.userdao;
import com.project.dto.reviewdto;
import com.project.util.ResponseStructure;

@Service
public class reviewservice {
	
	@Autowired
	private gamesdao gdao;
	@Autowired
	private userdao udao;
	
	@Autowired
	private reviewdao dao;
	
	@Autowired
	private ModelMapper mapper;

	public ResponseStructure<reviewdto> addreview(int userid, int gameId, reviewdto dto) {
		Optional<user> optuser = udao.getuserdata(userid);
		Optional<games> optgame = gdao.getgamesbyid(gameId);
		
		if(optuser.isPresent() && optgame.isPresent())
		{
			user user = optuser.get();
			games games = optgame.get();
			
			review rev = mapper.map(dto,review.class);
			
			rev.setGame(games);
			rev.setUser(user);
			
			review savedreview = dao.addreview(rev);
			
			reviewdto rdt = mapper.map(savedreview, reviewdto.class);
			ResponseStructure<reviewdto> structure = new ResponseStructure<reviewdto>();
			structure.setData(rdt);
			structure.setMessage("review added successfully");
			structure.setStatusCode(200);
			structure.setTimeStamp(LocalDateTime.now());
			return structure;
		}
		else
		{
			throw new IllegalArgumentException("no review added");
		}
	}

	public ResponseStructure<List<reviewdto>> getreviewbyname(String title) {
		Optional<games> optgame = gdao.getgamebyname(title);
		if(optgame.isPresent())
		{
			games game = optgame.get();
			
			List<review> reviews = dao.getReviewsByGame(game);
			 List<reviewdto> dtoList = reviews.stream()
		                .map(r -> mapper.map(r, reviewdto.class))
		                .collect(Collectors.toList());
			 
			 ResponseStructure<List<reviewdto>> structure = new ResponseStructure<>();
		        structure.setData(dtoList);
		        structure.setMessage("Reviews fetched successfully");
		        structure.setStatusCode(200);
		        structure.setTimeStamp(LocalDateTime.now());

		        return structure;
		    } 
		    else {
		        throw new IllegalArgumentException("Game not found");
		    }
			
			
		}

	public ResponseStructure<String> deletereview(int reviewid) {
		String message = dao.deletereview(reviewid);
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(message);
		structure.setMessage("review deleted");
		structure.setStatusCode(200);
		structure.setTimeStamp(LocalDateTime.now());
		return structure;
	}
	}





