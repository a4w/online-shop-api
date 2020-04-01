package fci.swe2.onlineshopapi.dataWrappers;

import fci.swe2.onlineshopapi.Admin;
import fci.swe2.onlineshopapi.Customer;
import fci.swe2.onlineshopapi.StoreOwner;

public class AllUsersWrapper{
    public Customer[] customers;
    public StoreOwner[] storeOwners;
    public Admin[] admins;
    public AllUsersWrapper(Customer[] customers, StoreOwner[] storeOwners, Admin[] admins){
        this.customers = customers;
        this.storeOwners = storeOwners;
        this.admins = admins;
    }
}
