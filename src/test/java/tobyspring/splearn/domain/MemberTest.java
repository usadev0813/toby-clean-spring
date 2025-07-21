package tobyspring.splearn.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    @Test
    void 멤버가_새로_만들어질떄_상태는_PENDING_이여야한다() {
        var member = new Member("usadev0813@gmail.com", "usadev", "secret");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

}