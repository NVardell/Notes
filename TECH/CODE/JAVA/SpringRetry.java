# Spring Scheduler URLs
+ http://www.baeldung.com/spring-scheduled-tasks
+ https://dzone.com/articles/running-on-time-with-springs-scheduled-tasks


# Spring Retry URLs
+ http://www.baeldung.com/spring-retry
+ (Testing) https://dzone.com/articles/spring-retry-ways-integrate


# Spring Boot - JPA/Hibernate URLs
+ (Testing) https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-one-mapping-example/


package com.other.spring.retry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

/**
 * TODO - Add Definition
 *
 * @author Nate Vardell
 * @since 6/16/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringRetryTests {

    @Autowired
    private RemoteCallService remoteCallService;

    @Test
    public void testRetry() throws Exception {
        String message = this.remoteCallService.call();
        verify(remoteCallService, times(3)).call();
        assertThat(message, is("Completed"));
    }

    @Configuration
    @EnableRetry
    public static class SpringConfig {

        @Bean
        public RemoteCallService remoteCallService() throws Exception {
            RemoteCallService remoteService = mock(RemoteCallService.class);
            when(remoteService.call())
                    .thenThrow(new RuntimeException("Remote Exception 1"))
                    .thenThrow(new RuntimeException("Remote Exception 2"))
                    .thenReturn("Completed");
            return remoteService;
        }
    }
}


package com.other.spring.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

/**
 * TODO - Add Definition
 *
 * @author Nate Vardell
 * @since 6/16/2018
 */
public interface RemoteCallService {

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000))
    String call() throws Exception;

}