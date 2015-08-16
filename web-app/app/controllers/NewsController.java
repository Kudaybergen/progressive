package controllers;

import play.data.parsing.MultipartStream;
import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class NewsController extends Controller {

	public static void news(){
    	List<News> news = News.findAll();
    	render(news);
    }

	public static void add(News news){
		news.createDate = new Date();
		render(news);
	}
	
	public static void edit(News news){
		render(news);
	}
	
	public static void save(News news,File img){
		news.createDate = new Date();
		news.save();
		news();
	}
	
	public static void delete(News news){
		System.out.println("delete");
		news.delete();
		news();
	}

}