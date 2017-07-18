package com.joaosakai.searchbar.api;

import com.joaosakai.searchbar.model.Contact;
import com.joaosakai.searchbar.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jgenari on 7/14/17.
 */
@RestController
@RequestMapping("/contacts")
public class ContactAPI {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getAllContacts() {
        return contactService.getContactList();
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public List<Contact> getContacts(@PathVariable String name) {
        return contactService.getContactsByName(name);
    }

    @RequestMapping(path = "/{name}/details")
    public Contact getContactByName(@PathVariable String name) {
        return contactService.getContactByName(name);
    }

}
