package co.infinum.workshop_4.modules;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by ivankocijan on 24.03.2014..
 */
@Table(name = "user")
public class User extends Model {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "description")
    private String description;

    public User (String name, String surname, String description) {
        this.name = name;
        this.surname = surname;
        this.description = description;
    }

    public User () {

    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname (String surname) {
        this.surname = surname;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }
}
