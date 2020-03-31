package fci.swe2.onlineshopapi.dataWrappers;

import fci.swe2.onlineshopapi.Admin;

public interface AdminWrapper {
    Admin getCustomer(String data);
    String getData(Admin customer);
}
