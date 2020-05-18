package com.stated.royally.temp;

import lombok.Builder;
import lombok.Data;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Contact Type Enum Usage Examples & Tests
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
public class ContactTypeTest {

    @Data @Builder static class Contact { int type, label; String value; }
    @Data @Builder static class Customer { List<Contact> contacts; }


    @Test
    public void whenGivenCustomer_returnWorkPhone() {
        List<Contact> contactList = Arrays.asList(
                Contact.builder().type(1).label(1).value("123-456-7890").build(),
                Contact.builder().type(2).label(4).value("987-654-3210").build()
        );
        Customer customer = Customer.builder().contacts(contactList).build();

        assertThat(getWorkPhone(customer), is("987-654-3210"));
    }

    /**
     * Get Work Phone for Customer
     * @param customer Business Customer
     * @return String value of customer's work phone number
     */
    protected String getWorkPhone(Customer customer) {
        if (!CollectionUtils.isEmpty(customer.getContacts())) {
            for(Contact contact : customer.getContacts()) {
                if(contact.getType() == ContactType.PHONE.getValue()
                    && contact.getLabel() == ContactLabel.WORK.getValue()) {
                    return contact.getValue();
                }
            }
        }
        return "9999999999";
    }

    /**
     * Get email for Customer
     * @param customer Business Customer
     * @return String value of customer's work phone number
     */
    protected String getEmail(Customer customer) {
        String email = "";
        if (!CollectionUtils.isEmpty(customer.getContacts())) {
            for(Contact contact : customer.getContacts()) {
                if(contact.getType() == ContactType.PHONE.getValue()
                           && contact.getLabel() == ContactLabel.WORK.getValue()) {
                    return contact.getValue();
                }
            }
        }
        return "9999999999";
    }
}
