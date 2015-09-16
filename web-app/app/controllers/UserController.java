package controllers;

import java.io.File;
import java.util.List;

import models.News;
import models.User;
import play.mvc.Controller;

public class UserController extends Controller{

	public static void users(){
    	List<User> users = User.findAll();
    	render(users);
	}
	
	public static void add(User user){
		render(user);
	}
	
	public static void save(User user){
		user.save();
		users();
	}
	
	public static void delete(User user){
		user.delete();
		users();
	}
	
}
