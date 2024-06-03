package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String provider;

    @CreationTimestamp // pc -> db (날짜주입)
    private Timestamp createdAt;

    @Builder
    public User(Timestamp createdAt, String provider, String email, String password, String username, Integer id) {
        this.createdAt = createdAt;
        this.provider = provider;
        this.email = email;
        this.password = password;
        this.username = username;
        this.id = id;
    }
}
