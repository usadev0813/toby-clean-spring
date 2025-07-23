package tobyspring.splearn.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
        member = Member.register(new MemberRegisterRequest("usadev0813@gmail.com", "usadev", "secret"), passwordEncoder);
    }

    @Test
    void 멤버가_새로_만들어질떄_상태는_PENDING_이여야한다() {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);

    }

    @Test
    void 멤버활성화() {
        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void 멤버활성화_실패() {
        member.activate();

        assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 멤버탈퇴() {
        member.activate();

        member.deactivated();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void 멤버탈퇴_실패() {
        assertThatThrownBy(member::deactivated).isInstanceOf(IllegalStateException.class);

        member.activate();
        member.deactivated();

        assertThatThrownBy(member::deactivated).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 비밀번호검증() {
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("hello", passwordEncoder)).isFalse();
    }

    @Test
    void 닉네임변경() {
        assertThat(member.getNickname()).isEqualTo("usadev");
        member.changeNickname("Maru");

        assertThat(member.getNickname()).isEqualTo("Maru");
    }

    @Test
    void 비밀번호변경() {
        member.changePassword("verysecret", passwordEncoder);

        assertThat(member.verifyPassword("verysecret", passwordEncoder)).isTrue();
    }

    @Test
    void 활성상태() {
        assertThat(member.isActive()).isFalse();

        member.activate();

        assertThat(member.isActive()).isTrue();

        member.deactivated();

        assertThat(member.isActive()).isFalse();
    }

    @Test
    void 잘못된이메일() {
        assertThatThrownBy(() ->
                Member.register(new MemberRegisterRequest("invalid email", "usadev", "secret"), passwordEncoder))
                .isInstanceOf(IllegalArgumentException.class);


        Member.register(new MemberRegisterRequest("usadev0813@gmail.com", "usadev", "secret"), passwordEncoder);
    }
}