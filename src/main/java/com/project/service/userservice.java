package com.project.service;


import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Entity.user;
import com.project.dao.userdao;
import com.project.dto.UserRequestDto;
import com.project.dto.UserResponseDto;
import com.project.util.ResponseStructure;
@Service
public class userservice {

	@Autowired
	private userdao dao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	public ResponseStructure<UserResponseDto> registeruser(UserRequestDto dto) 
	{
		user user = mapper.map(dto,user.class);
		user user1 = dao.registeruser(user);
		UserResponseDto dto1 = mapper.map(user1, UserResponseDto.class);
		ResponseStructure<UserResponseDto> structure = new ResponseStructure<UserResponseDto>();
		structure.setData(dto1);
		structure.setMessage("User Added Succesfully");
		structure.setStatusCode(201);
		structure.setTimeStamp(LocalDateTime.now());
		return structure;
	}


	public  ResponseStructure<UserResponseDto> getuserdata(int userid) {
	    Optional<user> user = dao.getuserdata(userid);
	    if(user.isPresent())
	    {
	    	user user1 = user.get();
	    	ResponseStructure<UserResponseDto> structure = new ResponseStructure<UserResponseDto>();
		    UserResponseDto dto = mapper.map(user1,UserResponseDto.class);
		    structure.setData(dto);
		    structure.setMessage("these are the details of the user");
		    structure.setStatusCode(200);
		    structure.setTimeStamp(LocalDateTime.now());
		    
			return structure;
	    	
	    	
	    }
	    else
	    {
	    	throw new IllegalArgumentException("no user found in this userid ");
	    }
	    
	}


	public ResponseStructure<UserResponseDto> updatedata(int userid,UserRequestDto dto) 
	{
	Optional<user> opt = dao.getuserdata(userid);
			if(opt.isPresent())
			{
				user olduser =opt.get();
				olduser.setEmail(dto.getEmail());
				
				user updatedUser = dao.updatedata(olduser);
			    UserResponseDto dto1 = mapper.map(updatedUser,UserResponseDto.class);
			    ResponseStructure<UserResponseDto> structure = new ResponseStructure<UserResponseDto>();
			    structure.setData(dto1);
			    structure.setMessage("data updated");
			    structure.setStatusCode(200);
			    structure.setTimeStamp(LocalDateTime.now());
			    return structure;
			    
			    
				
				
			}
			else
			{
				throw new IllegalArgumentException("There is no data in this user id try another userid"); 
			}
	}


	public ResponseStructure<String> deletedata(int userid) {
		String message = dao.deletedata(userid);
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(message);
		structure.setMessage("data deleted successfully");
		structure.setStatusCode(200);
		structure.setTimeStamp(LocalDateTime.now());
		return structure;
	}


	public ResponseStructure<UserResponseDto> findbyemail(String email) {
		Optional<user> opt = dao.findbyemail(email);
		if(opt.isPresent())
		{
			user user = opt.get();
			UserResponseDto dto = mapper.map(user, UserResponseDto.class);
			
			ResponseStructure<UserResponseDto> structure = new ResponseStructure<UserResponseDto>();
			structure.setData(dto);
			structure.setMessage("the details for this emailid");
			structure.setStatusCode(200);
			structure.setTimeStamp(LocalDateTime.now());
			
			return structure;
			
		}
		else
		{
			throw new IllegalArgumentException("no user found in this mailid");
		}
		
		
		
		
	}


	public ResponseStructure<UserResponseDto> findbyname(String username) {
	  Optional<user> opt = dao.findbyname(username);
	  if(opt.isPresent())
	  {
		  user user1 = opt.get();
		  ResponseStructure<UserResponseDto> structure = new ResponseStructure<UserResponseDto>();
		  UserResponseDto dto = mapper.map(user1, UserResponseDto.class);
				  structure.setData(dto);
		  structure.setMessage("the details for this  Username");
		  structure.setStatusCode(200);
		  structure.setTimeStamp(LocalDateTime.now());
		  return structure;
	  }
	  else
	  {
		  throw new IllegalArgumentException("no data found on this username");
	  }
	}


	public ResponseStructure<UserRequestDto> loginbymail(String email, String password) {
		user user = dao.loginbymail(email)
                .orElseThrow(() -> new IllegalArgumentException("No user found with this email"));
		if(user.getPassword().equals(password))
		{
			UserRequestDto dto = mapper.map(user, UserRequestDto.class);

		    ResponseStructure<UserRequestDto> structure = new ResponseStructure<>();
		    structure.setData(dto);
		    structure.setMessage("Login successful");
		    structure.setStatusCode(200);
		    structure.setTimeStamp(LocalDateTime.now());

		    return structure;
		}
		else
		{
			throw new IllegalArgumentException("no data found in this mail id");
		}
		}


	public ResponseStructure<UserRequestDto> loginbyname(String username, String password) {
		user user = dao.loginbyname(username)
				.orElseThrow(() -> new IllegalArgumentException("No user found with this username"));
		
		if(user.getPassword().equals(password))
		{
			UserRequestDto dto = mapper.map(user,UserRequestDto.class);
			
			ResponseStructure<UserRequestDto> structure = new ResponseStructure<UserRequestDto>();
			structure.setData(dto);
			structure.setMessage("data found using the username");
			structure.setStatusCode(200);
			structure.setTimeStamp(LocalDateTime.now());
			return structure;
		}
		else
		{
			throw new IllegalArgumentException("no data found in this mail id");
		}
		
	}
	
	}





