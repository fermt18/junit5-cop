package Junit5.Frameworks.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import Junit5.Extensions.MockitoExtension;
import LoginSystem.LoginRepository;
import LoginSystem.LoginService;
import LoginSystem.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.security.auth.login.LoginException;

@ExtendWith(MockitoExtension.class)
class LoginServiceChaptorTest {

    @InjectMocks
    LoginService loginService;

    @Mock
    LoginRepository loginRepository;

    @Captor
    ArgumentCaptor<UserForm> argCaptor;

    UserForm userForm = new UserForm("foo", "bar");

    @Test
    void testArgumentCaptor() throws LoginException {
        loginService.login(userForm);
        verify(loginRepository).login(argCaptor.capture());
        assertEquals(userForm, argCaptor.getValue());
    }

}
