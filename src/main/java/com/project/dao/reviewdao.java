package com.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Entity.games;
import com.project.Entity.review;
import com.project.Repository.reviewrepository;
import com.project.dto.reviewdto;
@Repository
public class reviewdao {

	@Autowired
	private reviewrepository revrep;
	
	public review addreview(review rev) {
		return revrep.save(rev);
	}

	public List<review> getReviewsByGame(games game) {
	    return revrep.findByGame(game);
	}

	public String deletereview(int reviewid) {
		Optional<review> opt = revrep.findById(reviewid);
		if(opt.isPresent())
		{
			 revrep.deleteById(reviewid);
			 return "deleted";
		}
		else
		{
			throw new IllegalArgumentException("no review found on this reviewid");
		}
	}


	

}
