package com.project.dto;

import lombok.Data;

@Data
public class UserRequestDto 
{
	private int userid;
	private String username;
	private String email;
	private String password;
	private String gender;
	private String Stream;
	private int Age;
}
