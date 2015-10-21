package models;

import play.db.jpa.GenericModel;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by tima on 8/14/15.
 */
public abstract class AbstractModel extends GenericModel {
    @Column(name = "create_date_")
    public Date createDate;
}
