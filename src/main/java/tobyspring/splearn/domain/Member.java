package tobyspring.splearn.domain;

import lombok.Getter;
import lombok.ToString;

import static java.util.Objects.requireNonNull;
import static org.springframework.util.Assert.state;

@Getter
@ToString
public class Member {
    private Email email;

    private String nickname;

    private String passwordHash;

    private MemberStatus status;

    private Member() {
    }

    public static Member register(MemberRegisterRequest createRequest, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.email = new Email(requireNonNull(createRequest.email()));
        member.nickname = requireNonNull(createRequest.nickname());
        member.passwordHash = requireNonNull(passwordEncoder.encode(createRequest.password()));

        member.status = MemberStatus.PENDING;

        return member;
    }

    public void activate() {
        state(status == MemberStatus.PENDING, "PENDING 상태가 아닙니다");

        this.status = MemberStatus.ACTIVE;
    }

    public void deactivated() {
        state(status == MemberStatus.ACTIVE, "ACTIVE 상태가 아닙니다");

        this.status = MemberStatus.DEACTIVATED;
    }

    public boolean verifyPassword(String secret, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(secret, passwordHash);
    }

    public void changeNickname(String nickname) {
        this.nickname = requireNonNull(nickname);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.passwordHash = passwordEncoder.encode(requireNonNull(password));
    }

    public boolean isActive() {
        return status == MemberStatus.ACTIVE;
    }
}
