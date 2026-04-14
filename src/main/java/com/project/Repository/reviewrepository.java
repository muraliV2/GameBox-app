package com.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.games;
import com.project.Entity.review;

public interface reviewrepository extends JpaRepository<review, Integer>{

	List<review> findByGame(games game);

	

}
