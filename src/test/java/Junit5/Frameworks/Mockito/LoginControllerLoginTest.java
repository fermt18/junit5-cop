package Junit5.Frameworks.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
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
class LoginControllerLoginTest {

    // Mocking objects
    @InjectMocks // SUT
    LoginController loginController;

    @Mock // mock SUT dependencies
    LoginService loginService;

    // Test data
    UserForm userForm = new UserForm("foo", "bar");

    @Test
    void testLoginOk() throws LoginException {
        // Setting expectations (stubbing methods)
        when(loginService.login(userForm)).thenReturn(true);

        // Exercise SUT
        String reseponseLogin = loginController.login(userForm);

        // Verification
        assertEquals("OK", reseponseLogin);
        verify(loginService).login(userForm);
        verifyNoMoreInteractions(loginService);
    }

    @Test
    void testLoginKo() throws LoginException {
        // Setting expectations (stubbing methods)
        when(loginService.login(userForm)).thenReturn(false);

        // Exercise SUT
        String reseponseLogin = loginController.login(userForm);

        // Verification
        assertEquals("KO", reseponseLogin);
        verify(loginService).login(userForm);
        verifyZeroInteractions(loginService);
    }
}
