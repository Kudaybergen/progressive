package controllers;

import play.Play;
import play.data.parsing.MultipartStream;
import play.mvc.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.filechooser.FileView;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.ivy.util.FileUtil;

import models.*;

public class NewsController extends Controller {

	public static void news(){
    	List<News> news = News.findAll();
    	render(news);
    }

	public static void add(News news){
		render(news);
	}
	
	public static void save(News news,File img){
		if(img != null){
			try {
				String path = Play.configuration.getProperty("progressive.images.path");
				FileUtil.forceDelete(new File(path + news.imagePath));
				String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), "jpg");
				FileUtils.moveFile(img, new File(path, name));
				news.imagePath = name;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		news.createDate = new Date();
		news.save();
		news();
	}

	public static void showImage(Long newsId) {
		 News news = News.findById(newsId);
		 String path = Play.configuration.getProperty("progressive.images.path");
		 File file = new File(path+"/"+news.imagePath);
		 renderBinary(file);
	}
	
	public static void delete(News news){
		news.delete();
		news();
	}

}