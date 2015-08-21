package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Required;

@Entity
@Table(name = "prog_info")
public class Info extends AbstractModel{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Required
    @Column
    public String content;
    
    @Required
    @Column
    public String title;
    
    @Required
    @Column
    public String info_type;
    
}
