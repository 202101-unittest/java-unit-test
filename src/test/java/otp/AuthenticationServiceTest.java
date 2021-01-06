package otp;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AuthenticationServiceTest {

    @Test
    public void is_valid_test() {
        FakeProfile profile = new FakeProfile();
        FakeToken token = new FakeToken();
        AuthenticationService target = new AuthenticationService(profile, token);

        boolean actual = target.isValid("joey", "91000000");

        assertTrue(actual);
    }

    private class FakeProfile implements Profile {

        @Override
        public String getPassword(String account) {
            if (account == "joey") {
                return "91";
            }
            return "";
        }
    }

    private class FakeToken implements Token {

        @Override
        public String getRandom(String account) {
            return "000000";
        }
    }
}
