package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import play.data.validation.Required;

@Entity
@Table(name = "prog_event")
public class Event extends AbstractModel{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Required
    @Column
    public String name;
    
    @Required
    @Column
    public String description;
    
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinTable(name = "events_groups")
	public List<Group> groups;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    @Required
	public List<BasicFile> files;
    
}
