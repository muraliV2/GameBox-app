package com.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Entity.games;
import com.project.Entity.user;

import com.project.dao.gamesdao;
import com.project.dao.userdao;
import com.project.dto.gamesdto;
import com.project.util.ResponseStructure;
@Service
public class gamesservice {
	

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private userdao usdao;
	
	@Autowired
	private gamesdao dao;

	public ResponseStructure<gamesdto> addgames(int userid, gamesdto dto) {
		Optional<user> opt = usdao.getuserdata(userid);
		 if(opt.isPresent())
		 {
			 user user = opt.get();
			 games game = mapper.map(dto,games.class);
			 game.setUser(user);
			 
			 games savedgames = dao.addgames(game);
			 
			 
			 gamesdto dto1 = mapper.map(savedgames,gamesdto.class);
			 ResponseStructure<gamesdto> structure = new ResponseStructure<gamesdto>();
			 structure.setData(dto1);
			 structure.setMessage("game added succesfull");
			 structure.setStatusCode(200);
			 structure.setTimeStamp(LocalDateTime.now());
			 return structure;
			 
			 
		 }
		 else
		 {
			 throw new  IllegalArgumentException("no user found");
		 }
		

	}

	public List<gamesdto> getgames() {
		return dao.getgames().stream()
				.map(game -> mapper.map(dao, gamesdto.class)).toList();
	}

	public ResponseStructure<String> deletegame(int GameId) {
		String message = dao.deletegame(GameId);
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(message);
		structure.setMessage("data deleted successfully");
		structure.setStatusCode(200);
		structure.setTimeStamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<gamesdto> getgamesbyid(int GameId) {
		Optional<games> opt = dao.getgamesbyid(GameId);
		if(opt.isPresent())
		{
			games game = opt.get();
			gamesdto dtoi = mapper.map(game, gamesdto.class);
			ResponseStructure<gamesdto> structure = new ResponseStructure<gamesdto>();
			structure.setData(dtoi);
			structure.setMessage("datas");
			structure.setStatusCode(200);
			structure.setTimeStamp(LocalDateTime.now());
			return structure;
			
		}
		else
		{
			throw new IllegalArgumentException("no data found in this GameId");
			
		}
	}

	public ResponseStructure<gamesdto> getgamebyname(String title) {
		Optional<games> opt = dao.getgamebyname(title);
		if(opt.isPresent())
		{
			games game = opt.get();
			gamesdto gto = mapper.map(game,gamesdto.class);
	        ResponseStructure<gamesdto> structure = new ResponseStructure<gamesdto>();
	        structure.setData(gto);
	        structure.setMessage("datas");
	        structure.setStatusCode(200);
	        structure.setTimeStamp(LocalDateTime.now());
	        return structure;
			
		}
		else
		{
			throw new IllegalArgumentException("no datas found on this gamename");
		}
	}

	public ResponseStructure<gamesdto> updategame(int gameId, gamesdto dto) {
		Optional<games> opt = dao.getgamesbyid(gameId);
		if(opt.isPresent())
		{
			games olddata = opt.get();
			olddata.setGenre(dto.getGenre());
			
			games newgame = dao.updatedata(olddata);
			gamesdto dto1 = mapper.map(newgame,gamesdto.class);
			ResponseStructure<gamesdto> structure = new ResponseStructure<gamesdto>();
			structure.setData(dto1);
			structure.setMessage("data updated successfully");
			structure.setStatusCode(200);
			structure.setTimeStamp(LocalDateTime.now());
			return structure;
			
			
			
		}
		else
		{
			throw new IllegalArgumentException("no data found in this gameid");
		}
	}



}
