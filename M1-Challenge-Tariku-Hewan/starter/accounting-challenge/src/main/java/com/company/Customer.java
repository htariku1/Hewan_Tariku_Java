package com.company;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;


public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        int sum = 0;
        for (AccountRecord record : charges) {
            sum += record.getCharge();
        }
        return sum;
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    public void addCharge(AccountRecord record) {
        charges.add(record);
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedBalance = numberFormat.format(getBalance());
        return "Customer [Id = " + id + ", Name = " + name + ", Balance = $" + formattedBalance + "]";
    }
}
