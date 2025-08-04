package tobyspring.splearn.application.required;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tobyspring.splearn.domain.Member;
import tobyspring.splearn.domain.MemberFIxture;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 멤버생성() {
        Member member = Member.register(MemberFIxture.createMemberRegisterRequest(), MemberFIxture.createPasswordEncoder());

        assertThat(member.getId()).isNull();

        memberRepository.save(member);

        assertThat(member.getId()).isNotNull();
    }
}