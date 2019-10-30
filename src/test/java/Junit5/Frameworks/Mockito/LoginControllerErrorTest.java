package Junit5.Frameworks.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

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
class LoginControllerErrorTest {

    @InjectMocks // SUT
    LoginController loginController;

    @Mock // DOC
    LoginService loginService;

    UserForm userForm = new UserForm("foo", "bar");

    @Test
    void testLoginError() {
        // Exercise
        String response = loginController.login(null);

        // Verify
        assertEquals("ERROR", response);
    }

    @Test
    void testLoginException() throws LoginException {
        // Expectation
        when(loginService.login(any(UserForm.class)))
                .thenThrow(IllegalArgumentException.class);

        // Exercise
        String response = loginController.login(userForm);

        // Verify
        assertEquals("ERROR", response);
    }

}
