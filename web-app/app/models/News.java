package models;

import play.data.validation.Required;

import javax.persistence.*;

/**
 * Created by tima on 8/14/15.
 */

@Entity
@Table(name = "prog_news")
public class News extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Required
    @Column
    public String title;

    @Required
    @Column(columnDefinition = "TEXT")
    public String body;

    @Required
    @Column(name = "image_path")
    public String imagePath;

}
