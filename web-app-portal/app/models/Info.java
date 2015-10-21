package models;

import play.data.validation.Required;

import javax.persistence.*;

@Entity
@Table(name = "prog_info")
public class Info extends AbstractModel {

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
