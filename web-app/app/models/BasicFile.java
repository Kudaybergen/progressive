package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Required;

@Entity
@Table(name = "prog_files")
public class BasicFile extends AbstractModel{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Required
    @Column
    public String name;
    
    @Required
    @Column
    public String pathName;
    
    @Required
    @Column
    public String contentType;
    
    @Required
    @Column
    public String path;
    
}
