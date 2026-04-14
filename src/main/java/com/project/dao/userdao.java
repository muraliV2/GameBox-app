package com.project.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Entity.user;
import com.project.Repository.userrepository;
@Repository
public class userdao {

	@Autowired
	private userrepository userrep;

	public user registeruser(user user) {
		user user1 = userrep.save(user);
		return user1;
	}



	
	public Optional<user> getuserdata(int userid) {
		return userrep.findById(userid);
	}








	public user updatedata(user olduser) {
		// TODO Auto-generated method stub
		return userrep.save(olduser);
	}




	public String deletedata(int userid) {
		Optional<user> opt = userrep.findById(userid);
		if(opt.isPresent())
		{
			userrep.deleteById(userid);
			return "User deleted";
			
		}
		else
		{
			throw new IllegalArgumentException("no user found in this id");
		}
		
	}









	public Optional<user> findbyemail(String email) {
		
		    return userrep.findByEmail(email);
		
	}









	public Optional<user> findbyname(String username) {
		return userrep.findByName(username);
	}




	public Optional<user> loginbymail(String email) {
		return userrep.findByEmail(email);
	}




	public Optional<user> loginbyname(String username) {
		return userrep.findByName(username);
	}

}
