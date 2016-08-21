package se.mbaeumer.glucometriq.models;

import javax.persistence.*;

/**
 * Created by martinbaumer on 27/07/16.
 */
@Entity
@Table(name="USERTYPE")
@javax.persistence.SequenceGenerator(name = "SEQ_USERTYPE", sequenceName = "SEQ_USERTYPE")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERTYPE")
    @Column(name = "ID")
    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
