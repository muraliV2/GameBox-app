package com.project.Entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Data
@Entity
public class user 
{
@Id
private int userid;
private String username;
private String email;
private String password;
private String gender;
private String Stream;
private int Age;



@OneToMany(mappedBy = "user")
private List<games> listofgame = new ArrayList<games>();

public void addgames(games game)
{
listofgame.add(game);
game.setUser(this);
}

public void removegame(games game)
{
listofgame.remove(game);
game.setUser(null);
}


@OneToMany(mappedBy = "user")
private List<review> listofreview = new ArrayList<review>();


public void addreview(review rev)
{
listofreview.add(rev);
rev.setUser(this);
}

public void removereview(review rev)
{
listofreview.remove(rev);
rev.setUser(null);
}
}



