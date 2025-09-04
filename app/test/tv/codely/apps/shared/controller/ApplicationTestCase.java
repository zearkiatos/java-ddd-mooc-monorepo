package tv.codely.apps.shared.controller;

import org.junit.runner.RunWith;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import javax.transaction.Transactional;

import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public abstract class ApplicationTestCase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventBus eventBus;

    @MockBean
    private MySqlDomainEventsConsumer mySqlDomainEventsConsumer;

    protected void assertResponse(String endpoint, Integer expectedStatusCode, String expectedResponse) throws Exception {
        System.out.println("testing endpoint: " + endpoint);
        ResultMatcher response = expectedResponse.isEmpty() ? content().string("") : content().json(expectedResponse);
        mockMvc.perform(get(endpoint))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(response);
    }

    protected void assertRequestWithBody(String method, String endpoint, String body, Integer expectedStatusCode)
		throws Exception {
		mockMvc
			.perform(request(HttpMethod.valueOf(method), endpoint).content(body).contentType(APPLICATION_JSON))
			.andExpect(status().is(expectedStatusCode))
			.andExpect(content().string(""));
	}

    protected void givenISendEventsToTheBus(DomainEvent<?>... domainEvents) {
        eventBus.publish(Arrays.asList(domainEvents));
    }

}
