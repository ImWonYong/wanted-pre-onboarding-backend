package wanted.preonboarding.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Builder
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
