package project.messenger.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @OneToMany(mappedBy = "sender")
    @JsonManagedReference // Добавьте эту аннотацию
    private List<Message> messages;

    public User(String username) {
        this.username = username;
    }
}


//@Entity
//@Data
//@NoArgsConstructor
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String username;
//    @OneToMany(mappedBy = "sender")
//    private List<Message> messages;
//
//    public User(String username) {
//        this.username = username;
//    }
//}

//@Entity
//@Data
//@NoArgsConstructor
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String username;
//    @OneToMany(mappedBy = "user")
//    private List<Message> messages;
//    public User(String username) {
//        this.username = username;
//    }
//
//}