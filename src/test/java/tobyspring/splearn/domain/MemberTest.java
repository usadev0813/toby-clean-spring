package tobyspring.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    @Test
    void 멤버가_새로_만들어질떄_상태는_PENDING_이여야한다() {
        var member = new Member("usadev0813@gmail.com", "usadev", "secret");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);

    }

    @Test
    void 멤버생성자_NULL_체크() {
        assertThatThrownBy(() -> new Member(null, "usadev", "secret"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void 멤버활성화() {
        var member = new Member("usadev0813@gmail.com", "usadev", "secret");

        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void 멤버활성화_실패() {
        var member = new Member("usadev0813@gmail.com", "usadev", "secret");

        member.activate();

        assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 멤버탈퇴() {
        var member = new Member("usadev0813@gmail.com", "usadev", "secret");
        member.activate();

        member.deactivated();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void 멤버탈퇴_실패() {
        var member = new Member("usadev0813@gmail.com", "usadev", "secret");

        assertThatThrownBy(member::deactivated).isInstanceOf(IllegalStateException.class);

        member.activate();
        member.deactivated();

        assertThatThrownBy(member::deactivated).isInstanceOf(IllegalStateException.class);
    }
}