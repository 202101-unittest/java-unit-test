package otp;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @Test
    public void is_valid_test() {
//        FakeProfile profile = new FakeProfile();
        Profile profile = Mockito.mock(Profile.class);
        when(profile.getPassword("joey")).thenReturn("91");

//        FakeToken token = new FakeToken();
        Token token = Mockito.mock(Token.class);
        when(token.getRandom(anyString())).thenReturn("000000");

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
