package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.UserRequestDto;
import com.project.dto.UserResponseDto;
import com.project.service.userservice;
import com.project.util.ResponseStructure;


@RestController
@RequestMapping("/user")
public class usercontroller 
{
	@Autowired
	private userservice usservice;
	@PostMapping("/register")
	public ResponseEntity<?> registeruser(@RequestBody UserRequestDto dto)
	{
		
		ResponseStructure<UserResponseDto> structure = usservice.registeruser(dto);
		return new ResponseEntity<>(structure,HttpStatus.CREATED);
	}

	@GetMapping("/get/{userid}")
	public ResponseEntity<?> getuserdata(@PathVariable int userid)
	{
		ResponseStructure<UserResponseDto> structure = usservice.getuserdata(userid);
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}
	
	@PutMapping("/update/{userid}")
	public ResponseEntity<?> updatedata(@PathVariable int userid,@RequestBody UserRequestDto dto )
	{
		ResponseStructure<UserResponseDto> structure = usservice.updatedata(userid,dto);
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{userid}")	
		public ResponseEntity<?> deletedata(@PathVariable int userid)
		{
			ResponseStructure<String> structure = usservice.deletedata(userid);
			return new ResponseEntity<>(structure,HttpStatus.OK);
			
		}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> findbyemail( @PathVariable  String email)
	{
		
		ResponseStructure<UserResponseDto> structure = usservice.findbyemail(email);
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}
	
	
	@GetMapping("/{username}")
	public ResponseEntity<?> findbyname(@PathVariable String  username)
	{
		ResponseStructure<UserResponseDto> structure = usservice.findbyname(username);
		return new ResponseEntity<>(structure,HttpStatus.OK);
		
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginbymail(@RequestBody UserRequestDto dto)
	{
		ResponseStructure<UserRequestDto> structure = usservice.loginbymail(dto.getEmail(),dto.getPassword());
		return  new ResponseEntity<>(structure,HttpStatus.OK);
		
	}
	
	@PostMapping("/loginbyname")
	public ResponseEntity<?> loginbyname(@RequestBody UserRequestDto dto)
	{
		ResponseStructure<UserRequestDto> structure = usservice.loginbyname(dto.getUsername(),dto.getPassword());
			return new ResponseEntity<>(structure,HttpStatus.OK);
		
	}
	
	
}
