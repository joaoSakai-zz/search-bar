package com.joaosakai.searchbar.service;

import com.joaosakai.searchbar.dao.ContactDAO;
import com.joaosakai.searchbar.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jgenari on 7/14/17.
 */
@Service
public class ContactService {

    @Autowired
    private ContactDAO contactDAO;

    public List<Contact> getContactsByName(final String contactName) {
        return contactDAO.getContactsByName(contactName);
    }

    public List<Contact> getContactList() {
        return contactDAO.getContacts();
    }

    public Contact getContactByName(final String name) {
        return contactDAO.getContactByName(name);
    }
}
