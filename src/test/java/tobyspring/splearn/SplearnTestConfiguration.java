package tobyspring.splearn;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import tobyspring.splearn.application.required.EmailSender;
import tobyspring.splearn.domain.MemberFIxture;
import tobyspring.splearn.domain.PasswordEncoder;

@TestConfiguration
public class SplearnTestConfiguration {

    @Bean
    public EmailSender emailSender() {
        return (email, subject, body) -> System.out.printf("이메일을 전송했습니다.");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return MemberFIxture.createPasswordEncoder();
    }
}
