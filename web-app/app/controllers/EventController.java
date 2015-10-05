package controllers;

import play.Play;
import play.data.parsing.MultipartStream;
import play.mvc.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.filechooser.FileView;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.ivy.util.FileUtil;

import com.ning.http.multipart.FilePart;

import models.*;

public class EventController extends Controller {

	public static void events() {
		List<Event> events = Event.findAll();
		render(events);
	}

	public static void add(Event event) {
		List<Group> groups = Group.findAll();
		render(event, groups);
	}

	public static void save(Event event) {

		File[] files = params.get("img", File[].class);

		if (files != null) {
			List<BasicFile> filesPath = new ArrayList<>();
			try {
				for (File f : files) {
					String path = Play.configuration.getProperty("progressive.images.path");
					BasicFile bf = new BasicFile();
					bf.name = f.getName();
					bf.path = path;
					
					String ext = FilenameUtils.getExtension(f.getAbsolutePath());

					String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
					File file = new File(path, name);
					FileUtils.moveFile(f, file);
					bf.pathName = name;
					if (bf.name.contains("jpg")) {
						bf.contentType = "jpg";
					} else if (bf.name.contains("png")) {
						bf.contentType = "png";
					} else if (bf.name.contains("mp3")) {
						bf.contentType = "mp3";
					} else if (bf.name.contains("mp4")) {
						bf.contentType = "mp4";
					} else if (bf.name.contains("ogv")) {
						bf.contentType = "ogg";
					} else if (bf.name.contains("webm")) {
						bf.contentType = "webm";
					} else if (bf.name.contains("3gp")) {
						bf.contentType = "3gp";
					}

					bf.save();
					filesPath.add(bf);
				}
				if(event.files == null){
					event.files = filesPath;
				}else{
					event.files.addAll(filesPath);
				}
				} catch (IOException e) {
				e.printStackTrace();
			}
		}
		event.createDate = new Date();
		event.save();
		events();
	}

	public static void getFile(Long fileId) {
		BasicFile f = BasicFile.findById(fileId);
		File file = new File(f.path + "/" + f.pathName);
		renderBinary(file);
	}

	public static void delete(Event event) {
		List<BasicFile> files = event.files;
		event.files.clear();
		event.delete();

		events();
	}

}