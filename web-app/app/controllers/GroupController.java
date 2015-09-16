package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import models.Group;
import models.News;
import models.User;
import play.mvc.Controller;

public class GroupController extends Controller {

	public static void groups() {
		List<Group> groups = Group.findAll();
		for (Group g : groups) {
			System.out.println(g.students.size());
		}
		render(groups);
	}

	public static void add(Group group) {
		List<User> teachers = null;
		List<User> students = null;
		teachers = User.find("role like 'Учитель'").fetch();
		students = User.find("role like 'Ученик'").fetch();

		render(group, students, teachers);
	}

	public static void getTeachers() {
		List<Group> teachers = null;
		teachers = Group.findAll();
		renderJSON(teachers);
	}

	public static void save(Group group) {
		if (group.id == null) {
			group.save();
		} else {
			if (group.students != null) {
				List<User> students = group.students;
				System.out.println("students " + students);
				group.students = null;
				group.save();
				group.students = students;
			}
			group.save();
		}
		groups();
	}

	public static void delete(Group group) {
		group.delete();
		groups();
	}

}
