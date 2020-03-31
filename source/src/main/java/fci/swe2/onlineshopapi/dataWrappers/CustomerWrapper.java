package fci.swe2.onlineshopapi.dataWrappers;

import fci.swe2.onlineshopapi.Customer;

public interface CustomerWrapper {
    Customer getCustomer(String data);
    String getData(Customer customer);
}
