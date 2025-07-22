package tobyspring.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void 동등성() {
        Email email1 = new Email("usadev0813@gmail.com");
        Email email2 = new Email("usadev0813@gmail.com");

        assertThat(email1).isEqualTo(email2);
    }

}