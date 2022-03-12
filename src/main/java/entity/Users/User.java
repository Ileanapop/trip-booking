package entity.Users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    public User(){}

    public User(String id, String firstName, String lastName, String email,String username, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String email,String username, String password){
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
