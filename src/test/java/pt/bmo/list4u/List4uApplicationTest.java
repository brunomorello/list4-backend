package pt.bmo.list4u;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class List4uApplicationTest {

    // TODO: create test profile for it

    @Test
    void applicationStarts() {
        try (MockedStatic<SpringApplication> mockedStatic = Mockito.mockStatic(SpringApplication.class)) {
            mockedStatic.when(() -> SpringApplication.run(List4uApplicationTest.class, new String[] { "foo", "bar" }))
                    .thenReturn(Mockito.mock(ConfigurableApplicationContext.class));

            List4uApplication.main(new String[] { "${HEROKU_POSTGRESQL_GRAY_URL}", "bar" });

            mockedStatic.verify(() -> SpringApplication.run(List4uApplication.class, new String[] { "foo", "bar" }));
        }
    }
}