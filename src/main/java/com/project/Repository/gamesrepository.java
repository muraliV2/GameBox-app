package com.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Entity.games;

public interface gamesrepository extends JpaRepository<games,Integer> {



	Optional<games> findByTitle(String title);

}
