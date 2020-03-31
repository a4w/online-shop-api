package fci.swe2.onlineshopapi.dataWrappers;

import fci.swe2.onlineshopapi.StoreOwner;

public interface StoreOwnerWrapper {
    StoreOwner getCustomer(String data);
    String getData(StoreOwner customer);
}
