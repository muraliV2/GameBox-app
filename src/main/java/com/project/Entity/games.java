package com.project.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Data
@Entity
public class games {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int GameId;
private String title;
private String genre;
private String platform;
private String Releasedyear;


@ManyToOne
@JoinColumn(name = "user_id")
private user user;

public void adduser(user user)
{
this.user = user;
user.getListofgame().add(this);
}

@OneToMany(mappedBy = "game")
private List<review> gamereviews = new ArrayList<review>();

public void addgamereview(review rev)
{
gamereviews.add(rev);
rev.setGame(this);

}

public void removegamereview(review rev)
{
gamereviews.remove(rev);
rev.setGame(null);
}

}
