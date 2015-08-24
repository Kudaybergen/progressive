package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Required;

@Entity
@Table(name = "prog_users")
public class User extends AbstractModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

	@Required
    @Column
    public String role;
	
	@Required
    @Column
    public String firstName;
	
	@Required
    @Column
    public String lastName;
	
	@Required
    @Column
    public String phone;
	
	@Required
    @Column
    public String email;
	
	@Required
    @Column
    public String password;
	
}
