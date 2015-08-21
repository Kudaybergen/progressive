package controllers;

import java.util.Date;
import java.util.List;

import models.Info;
import models.News;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.Controller;

public class InfoController extends Controller {

	public static void about(){
		Info info = Info.find("from Info u where u.info_type  like ?", "about").first();
		Info faq = Info.find("from Info u where u.info_type  like ?", "faq").first();
		Info contacts = Info.find("from Info u where u.info_type  like ?", "contacts").first();
    	render(info,faq,contacts);
    }
	
	public static void saveInfo(Info info){
		info.createDate = new Date();
		info.save();
		about();
    }
	public static void saveFaq(Info faq){
		faq.createDate = new Date();
		faq.save();
		about();
    }
	public static void saveContacts(Info contacts){
		contacts.createDate = new Date();
		contacts.save();
		about();
    }
}
