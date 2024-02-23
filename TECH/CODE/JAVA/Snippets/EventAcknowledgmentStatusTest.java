package com.stated.royally.temp;

import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.stated.royally.temp.EventAcknowledgmentStatus.getValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Event Acknowledgment Status Enum Unit Tests
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
public class EventAcknowledgmentStatusTest {

    @Test
    public void getFailedStatus() {
        assertEventAcknowledgmentStatus(getValue("FAIL"), EventAcknowledgmentStatus.FAIL);
        assertEventAcknowledgmentStatus(getValue("fail"), EventAcknowledgmentStatus.FAIL);
    }

    @Test
    public void getSuccessStatus() {
        assertEventAcknowledgmentStatus(getValue("SUCCESS"), EventAcknowledgmentStatus.SUCCESS);
        assertEventAcknowledgmentStatus(getValue("success"), EventAcknowledgmentStatus.SUCCESS);
    }

    @Test
    public void getUnknownStatus() {
        assertEventAcknowledgmentStatus(getValue("Non-Existent"), EventAcknowledgmentStatus.UNKNOWN);
    }

    /**
     * Assert string values equate to the correct Enum Status.
     *
     * @param status Event Status (Driven by Overall Status of Entity Acknowledgment List.)
     * @param expectedStatus Expected Enum Status
     */
    private void assertEventAcknowledgmentStatus(EventAcknowledgmentStatus status, EventAcknowledgmentStatus expectedStatus) {
        assertThat(status.getStatus(), equalTo(expectedStatus.getStatus()));
        assertThat(status.getStatusCode(), equalTo(expectedStatus.getStatusCode()));
    }


    @Test
    public void givenEntityAcknowledgmentList_withFailedEvent_returnFailStatus() {
        List<EntityAcknowledgment> entityAcknowledgments = Arrays.asList(
                EntityAcknowledgment.builder().status(EventAcknowledgmentStatus.SUCCESS).build(),
                EntityAcknowledgment.builder().status(EventAcknowledgmentStatus.FAIL).build()
        );

        assertEventAcknowledgmentStatus(getStatusCode(entityAcknowledgments), EventAcknowledgmentStatus.FAIL);
    }

    @Test
    public void givenEntityAcknowledgmentList_withNoFailures_returnSuccessStatus() {
        List<EntityAcknowledgment> entityAcknowledgments = Arrays.asList(
                EntityAcknowledgment.builder().status(EventAcknowledgmentStatus.SUCCESS).build(),
                EntityAcknowledgment.builder().status(EventAcknowledgmentStatus.SUCCESS).build()
        );

        assertEventAcknowledgmentStatus(getStatusCode(entityAcknowledgments), EventAcknowledgmentStatus.SUCCESS);
    }


    /**
     * Get a single status code from list of entity acknowledgments.
     *
     * If any of the acknowledgments have a failed status, we return FAILED status
     * so that we can run reconciliation for that specific business entity.
     *
     * @param entityAcknowledgments Event acknowledgments for a specific entity
     * @return EventAcknowledgmentStatus Success/Failure
     */
    private EventAcknowledgmentStatus getStatusCode(List<EntityAcknowledgment> entityAcknowledgments) {
        return entityAcknowledgments.stream().filter(entity -> EventAcknowledgmentStatus.FAIL.equals(entity.getStatus()))
                       .map(EntityAcknowledgment::getStatus).findAny().orElse(EventAcknowledgmentStatus.SUCCESS);
    }

    @Data
    @Builder
    private static class EntityAcknowledgment {
        private EventAcknowledgmentStatus status;

    }
}
