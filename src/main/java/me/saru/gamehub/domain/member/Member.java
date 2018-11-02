package me.saru.gamehub.domain.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member {
    private static final String YYYY_MM_DD = "yyyy.MM.dd";

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    private String nickName;

    @Column(nullable = false)
    private String email;

    @CreatedDate
    @JsonFormat(pattern = YYYY_MM_DD)
    private LocalDate createDate;

    public Member() {
    }

    public Member(String userId, String name, String nickName, String email) {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }
}
