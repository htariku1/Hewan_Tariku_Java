package com.company;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {

    @Test
    public void testGetPositiveBalance() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test");

        AccountRecord record1 = new AccountRecord();
        record1.setCharge(100);
        customer.addCharge(record1);

        AccountRecord record2 = new AccountRecord();
        record2.setCharge(50);
        customer.addCharge(record2);

        assertEquals(150, customer.getBalance());
    }

    @Test
    public void testGetNegativeBalance() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test");

        AccountRecord record1 = new AccountRecord();
        record1.setCharge(-100);
        customer.addCharge(record1);

        AccountRecord record2 = new AccountRecord();
        record2.setCharge(-50);
        customer.addCharge(record2);

        assertEquals(-150, customer.getBalance());
    }

    @Test
    public void testToStringWithPositiveBalance() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test");

        AccountRecord record = new AccountRecord();
        record.setCharge(100);
        customer.addCharge(record);

        String expected = "Customer [Id = 1, Name = Test, Balance = $100]";
        assertEquals(expected, customer.toString());
    }

    @Test
    public void testToStringWithNegativeBalance() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Test");

        AccountRecord record = new AccountRecord();
        record.setCharge(-100);
        customer.addCharge(record);

        String expected = "Customer [Id = 1, Name = Test, Balance = $-100]";
        assertEquals(expected, customer.toString());
    }
}
