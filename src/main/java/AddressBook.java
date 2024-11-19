package com.addressbook.model;

import java.util.TreeSet;

public class AddressBook extends TreeSet<FriendPO> {
    public AddressBook() {
        super();
    }

    @Override
    public boolean add(FriendPO friend) {
        return super.add(friend);
    }
}