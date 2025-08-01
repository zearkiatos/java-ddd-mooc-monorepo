package tv.codely.apps.mooc.controller.greeter;

import org.junit.jupiter.api.Test;

import tv.codely.apps.mooc.controller.RequestTestCase;

final class GreeterControllerShould extends RequestTestCase {
    @Test
    void should_return_a_message_with_a_name() throws Exception {
        assertTextResponse("/greeter?name=Pedro", 200, "Hello, Pedro");
    }
}
