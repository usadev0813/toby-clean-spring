package tobyspring.splearn.application.required;

import tobyspring.splearn.domain.Email;

/**
 * 이메일을 전송한다
 */
public interface EmailSender {
    void send(Email email, String subject, String body);
}
