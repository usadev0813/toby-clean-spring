package tobyspring.splearn.application.provided;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import tobyspring.splearn.SplearnTestConfiguration;
import tobyspring.splearn.domain.Member;
import tobyspring.splearn.domain.MemberFIxture;
import tobyspring.splearn.domain.MemberStatus;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(SplearnTestConfiguration.class)
public class MemberRegisterTest {

    @Autowired
    MemberRegister memberRegister;


    @Test
    void register() {
        Member member = memberRegister.register(MemberFIxture.createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

}
