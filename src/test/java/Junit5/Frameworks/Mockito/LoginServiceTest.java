package Junit5.Frameworks.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import Junit5.Extensions.MockitoExtension;
import LoginSystem.LoginRepository;
import LoginSystem.LoginService;
import LoginSystem.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.security.auth.login.LoginException;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @InjectMocks
    LoginService loginService;

    @Mock
    LoginRepository loginRepository;

    UserForm userForm = new UserForm("foo", "bar");

    @Test
    void testLoginOk() throws LoginException {
        when(loginRepository.login(any(UserForm.class))).thenReturn(true);
        assertTrue(loginService.login(userForm));
        verify(loginRepository, atLeast(1)).login(userForm);
    }

    @Test
    void testLoginKo() throws LoginException {
        when(loginRepository.login(any(UserForm.class))).thenReturn(false);
        assertFalse(loginService.login(userForm));
        verify(loginRepository, times(1)).login(userForm);
    }

    @Test
    void testLoginTwice() {
        when(loginRepository.login(userForm)).thenReturn(true);
        assertThrows(LoginException.class, () -> {
            loginService.login(userForm);
            loginService.login(userForm);
        });
    }
}
