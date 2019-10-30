package Junit5.Frameworks.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import Junit5.Extensions.MockitoExtension;
import LoginSystem.LoginController;
import LoginSystem.LoginService;
import LoginSystem.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.security.auth.login.LoginException;


@ExtendWith(MockitoExtension.class)
class LoginControllerBDDTest {

    @InjectMocks
    LoginController loginController;

    @Mock
    LoginService loginService;

    UserForm userForm = new UserForm("foo", "bar");

    @Test
    void testLoginOk() throws LoginException {
        given(loginService.login(userForm)).willReturn(true);
        assertEquals("OK", loginController.login(userForm));
    }

    @Test
    void testLoginKo() throws LoginException {
        given(loginService.login(userForm)).willReturn(false);
        assertEquals("KO", loginController.login(userForm));
    }

    @Test
    void testLoginError() {
        assertEquals("ERROR", loginController.login(null));
    }

    @Test
    void testLoginException() throws LoginException {
        given(loginService.login(any(UserForm.class)))
                .willThrow(IllegalArgumentException.class);
        assertEquals("ERROR", loginController.login(userForm));
    }

}
