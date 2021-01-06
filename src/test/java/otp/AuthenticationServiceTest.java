package otp;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    private Profile profile = Mockito.mock(Profile.class);
    private Token token = Mockito.mock(Token.class);
    private MyNotification notification = Mockito.mock(MyNotification.class);
    private AuthenticationService target = new AuthenticationService(profile, token, notification);

    @Test
    public void is_valid() {
        givenPassword("joey", "91");
        givenToken("000000");
        shouldBeValid("joey", "91000000");
    }

    @Test
    public void is_invalid() {
        givenPassword("joey", "91");
        givenToken("000000");
        shouldBeInvalid("joey", "wrong password");
    }

    @Test
    public void notify_user_when_invalid() {
        whenInvalid("joey");
        shouldNotify("joey", "login failed");
    }

    private void shouldNotify(String account, String status) {
        ArgumentCaptor<String> captor = forClass(String.class);
        verify(notification).notify(captor.capture());
        String message = captor.getValue();

        assertThat(message).contains(account, status);
    }

    private void whenInvalid(String account) {
        givenPassword(account, "91");
        givenToken("000000");
        target.isValid("joey", "wrong password");
    }

    private void shouldBeInvalid(String account, String password) {
        boolean actual = target.isValid(account, password);
        assertFalse(actual);
    }

    private void shouldBeValid(String account, String password) {
        boolean actual = target.isValid(account, password);
        assertTrue(actual);
    }

    private void givenToken(String token) {
        when(this.token.getRandom(anyString())).thenReturn(token);
    }

    private void givenPassword(String account, String password) {
        when(profile.getPassword(account)).thenReturn(password);
    }
}
