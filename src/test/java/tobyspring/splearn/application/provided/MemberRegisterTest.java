package tobyspring.splearn.application.provided;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import tobyspring.splearn.SplearnTestConfiguration;
import tobyspring.splearn.domain.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
record MemberRegisterTest(MemberRegister memberRegister, EntityManager entityManager) {

    @Test
    void register() {
        Member member = memberRegister.register(MemberFIxture.createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void duplicateEmailFail() {
        Member member = memberRegister.register(MemberFIxture.createMemberRegisterRequest());

        assertThatThrownBy(() -> memberRegister.register(MemberFIxture.createMemberRegisterRequest()))
                .isInstanceOf(DuplicateEmailException.class);

    }

    @Test
    void activate() {
        Member member = memberRegister.register(MemberFIxture.createMemberRegisterRequest());
        entityManager.flush();
        entityManager.clear();

        member = memberRegister.activate(member.getId());

        entityManager.flush();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);;
    }

    @Test
    void memberRegisterFail() {
        checkValidation(new MemberRegisterRequest("usadev0813@gmail.com", "usadev", "secret") );
        checkValidation(new MemberRegisterRequest("usadev0813@gmail.com", "usadevaazaasasasa", "secret") );
        checkValidation(new MemberRegisterRequest("usadev0813!?!!@gmail.com", "usadevaazaasasasa", "secret") );
    }

    private void checkValidation(MemberRegisterRequest invalid) {
        assertThatThrownBy(() -> memberRegister.register(invalid))
            .isInstanceOf(ConstraintViolationException.class);
    }

}
