package controllers;

import play.Play;
import play.data.parsing.MultipartStream;
import play.db.jpa.Transactional;
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

public class TutorialsController extends Controller {

	public static void tutorials() {
		List<Tutorial> tutorials = Tutorial.findAll();
		render(tutorials);
	}

	public static void add(Tutorial tutorial) {
		render(tutorial);
	}

	public static void save(Tutorial tutorial) {

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
				if(tutorial.files == null){
					tutorial.files = filesPath;
				}else{
					tutorial.files.addAll(filesPath);
				}
				} catch (IOException e) {
				e.printStackTrace();
			}
		}
		tutorial.createDate = new Date();
		tutorial.save();
		tutorials();
	}

	public static void getFile(Long fileId) {
		BasicFile f = BasicFile.findById(fileId);
		File file = new File(f.path + "/" + f.pathName);
		renderBinary(file);
	}

	public static void delete(Tutorial tutorial) {
		List<BasicFile> files = tutorial.files;
		tutorial.files.clear();
		tutorial.save();
		for(BasicFile bf : files){
			BasicFile f = BasicFile.findById(bf.id);
			File file = new File(f.path + "/" + f.pathName);
			file.delete();
			f.delete();
		}
		tutorial.delete();
		tutorials();
	}

}