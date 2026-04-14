package com.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Entity.user;


public interface userrepository extends JpaRepository<user,Integer>
{
	Optional<user> findByEmail(String email);

	Optional<user> findByName(String username);
}
