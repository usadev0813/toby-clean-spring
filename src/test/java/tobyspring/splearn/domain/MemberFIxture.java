package tobyspring.splearn.domain;

public class MemberFIxture {
    public static MemberRegisterRequest createMemberRegisterRequest(String mail) {
        return new MemberRegisterRequest(mail, "usadev", "secret");
    }

    public static MemberRegisterRequest createMemberRegisterRequest() {
        return createMemberRegisterRequest("usadev0813@gmail.com");
    }

    public static PasswordEncoder createPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
    }
}
