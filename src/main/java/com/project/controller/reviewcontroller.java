package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.reviewdto;
import com.project.service.reviewservice;
import com.project.util.ResponseStructure;

@RestController


@RequestMapping("/review")
public class reviewcontroller 
{
@Autowired

private reviewservice revservice;


@PostMapping("/add/{userid}/{GameId}")
public ResponseEntity<?> addreview(@PathVariable int userid,@PathVariable int GameId,@RequestBody reviewdto dto)
{
ResponseStructure<reviewdto> structure = revservice.addreview(userid,GameId,dto);
return new ResponseEntity<>(structure,HttpStatus.CREATED);
}

@GetMapping("/game/{title}")
public ResponseEntity<ResponseStructure<List<reviewdto>>> getReviewsByGameName(
        @PathVariable String title) {

    ResponseStructure<List<reviewdto>> structure = revservice.getreviewbyname(title);
    return new ResponseEntity<>(structure, HttpStatus.OK);
}
@DeleteMapping("/deletereview")
public ResponseEntity<?> deletereview(@PathVariable int Reviewid)
{
ResponseStructure<String> structure = revservice.deletereview(Reviewid);
return new ResponseEntity<>(structure,HttpStatus.OK);
}
}



