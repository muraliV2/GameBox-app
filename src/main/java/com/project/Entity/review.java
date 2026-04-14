package com.project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity
public class review 
{
@Id
private int Reviewid;
private int rating;
private String comment;
private String ReviewDate;

@ManyToOne
@JoinColumn(name = "user_id")
private user user;

public void adduser(user user)
{
	this.user = user;
	user.getListofreview().add(this);
}

@ManyToOne
@JoinColumn(name = "game_id")
private games game;

public void addgame(games game)
{
this.game = game;
game.getGamereviews().add(this);
}
}
