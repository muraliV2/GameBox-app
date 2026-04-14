package com.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Entity.games;
import com.project.Entity.user;
import com.project.Repository.gamesrepository;
import com.project.dto.gamesdto;
@Repository
public class gamesdao {
	
	@Autowired
	private gamesrepository gamesrepo;


	public games addgames(games game) {
		
		return gamesrepo.save(game);
	}


	  public List<games> getgames() {
	        return gamesrepo.findAll();
	    }


	  public String deletegame(int gameId) {
		Optional<games> opt = gamesrepo.findById(gameId);
		if(opt.isPresent())
		{
			gamesrepo.deleteById(gameId);
			return "data deleted";
			
			
		}
		else
		{
			throw new IllegalArgumentException("no game found on this id");
			
		}
		
		
	  }


	  public Optional<games> getgamesbyid(int gameId) {
		return gamesrepo.findById(gameId);
	  }


	  public Optional<games> getgamebyname(String title) {
		return gamesrepo.findByTitle(title);
	  }


	  public games updatedata(games olddata) {
		return gamesrepo.save(olddata);
	  }

}
