package com.fashionblogweek9.dtos.userdtos;

import com.fashionblogweek9.enums.Role;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Data
@EntityListeners(AuditingEntityListener.class)
public class UserSignupDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String gender;

    @CreationTimestamp
    private Timestamp createdAt;
}
