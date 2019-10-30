package Junit5.Frameworks.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Junit5.Extensions.MockitoExtension;
import LoginSystem.LoginRepository;
import LoginSystem.LoginService;
import LoginSystem.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;

@ExtendWith(MockitoExtension.class)
class LoginServiceSpyTest {

    @InjectMocks
    LoginService loginService;

    @Spy
    LoginRepository loginRepository;

    UserForm userOk = new UserForm("user1", "p1");
    UserForm userKo = new UserForm("foo", "bar");

    @Test
    void testLoginOk() throws Throwable {
        assertTrue(loginService.login(userOk));
    }

    @Test
    void testLoginKo() throws Throwable {
        assertFalse(loginService.login(userKo));
    }
}
