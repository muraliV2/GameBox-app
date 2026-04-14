package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Entity.games;
import com.project.dto.gamesdto;
import com.project.service.gamesservice;
import com.project.util.ResponseStructure;
@RestController
@RequestMapping("/games")
public class gamesController 
{
@Autowired
private gamesservice gameservice;


@PostMapping("/add/{userid}")
public ResponseEntity<?> addgames(@PathVariable int userid,@RequestBody gamesdto dto)
{
ResponseStructure<gamesdto> structure = gameservice.addgames(userid,dto);
return new ResponseEntity<>(structure,HttpStatus.OK);

}

@GetMapping("/getgames")
public List<gamesdto> getgames()
{
	return gameservice.getgames();
}


@DeleteMapping("/{GameId}")
public ResponseEntity<?> deletegame(@PathVariable int GameId)
{
ResponseStructure<String> structure = gameservice.deletegame(GameId);
return new ResponseEntity<>(structure,HttpStatus.OK);
}

@GetMapping("/get/{GameId}")
public ResponseEntity<?> getgamesbyid(@PathVariable int GameId)
{
ResponseStructure<gamesdto> structure = gameservice.getgamesbyid(GameId);
return new ResponseEntity<>(structure,HttpStatus.OK);

}

@GetMapping("/get/{title}")
public ResponseEntity<?> getgamebyname(@PathVariable String title)
{
ResponseStructure<gamesdto> structure = gameservice.getgamebyname(title);
return new ResponseEntity<>(structure,HttpStatus.OK);
}

@PutMapping("update/{GameId}")
public ResponseEntity<?> updategame(@PathVariable int GameId,@RequestBody gamesdto dto)
{
	ResponseStructure<gamesdto> structure = gameservice.updategame(GameId,dto);
	return new ResponseEntity<>(structure,HttpStatus.OK);
}


}
