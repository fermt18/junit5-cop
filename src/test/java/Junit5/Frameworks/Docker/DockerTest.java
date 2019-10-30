package Junit5.Frameworks.Docker;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.github.junit5docker.Docker;
import com.github.junit5docker.Environment;
import com.github.junit5docker.Port;
import com.github.junit5docker.WaitFor;
// not working for Windows?
// second option: https://www.baeldung.com/docker-test-containers
@Docker(image = "mysql", ports = @Port(exposed = 8801, inner = 3306), environments = {
        @Environment(key = "MYSQL_ROOT_PASSWORD", value = "root"),
        @Environment(key = "MYSQL_DATABASE", value = "testdb"),
        @Environment(key = "MYSQL_USER", value = "testuser"),
        @Environment(key = "MYSQL_PASSWORD", value = "secret"), },
        waitFor = @WaitFor("mysqld: ready for connections"))

@Disabled("Failing test in Windows (dll missing)")
public class DockerTest {

    @Test
    void test() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:8801/testdb", "testuser",
                "secret");
        assertFalse(connection.isClosed());
        connection.close();
    }
}