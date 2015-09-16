package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Required;

@Entity
@Table(name = "prog_groups")
public class Group extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@Required
	@Column
	public String name;

	@Required
	@Column
	public String about;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	public User teacher;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinTable(name = "students")
	public List<User> students;

}
