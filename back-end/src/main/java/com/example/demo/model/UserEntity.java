package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="sysytem-uuid", strategy = "uuid")
    private String id; // 사용자에게 고유하게 부여되는 id
    private String username; // 사용자의 이름
    private String email; // 사용자의 email, 아이디와 같은 기능
    private String password; // 패스워드

}
