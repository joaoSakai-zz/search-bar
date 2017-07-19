package com.joaosakai.searchbar.dao;

import com.joaosakai.searchbar.components.ElasticsearchComponent;
import com.joaosakai.searchbar.model.Contact;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jgenari on 7/12/17.
 */
@Repository
public class ContactDAO {

    private static final String CONTACTS_INDEX = "contacts";

    @Autowired
    private ElasticsearchComponent elasticsearchComponent;

    public List<Contact> getContactsByName(final String contactName) {
        SearchResponse response = elasticsearchComponent.getClient().prepareSearch(CONTACTS_INDEX)
                .setQuery(QueryBuilders.matchPhrasePrefixQuery("name", contactName))
                .setSize(100)
                .get();

        List<Contact> contacts = new ArrayList<>();

        for(SearchHit hit : response.getHits()) {
            Map<String, Object> sourceResult = hit.getSource();
            contacts.add(new Contact(
                    String.valueOf(hit.getId()),
                    String.valueOf(sourceResult.get("name"))
            ));
        }

        return contacts;
    }

    public List<Contact> getContacts() {
        SearchResponse response = elasticsearchComponent.getClient().prepareSearch(CONTACTS_INDEX)
                .setQuery(QueryBuilders.matchAllQuery())
                .setSize(100)
                .get();

        List<Contact> contacts = new ArrayList<>();

        for(SearchHit hit : response.getHits()) {
            Map<String, Object> sourceResult = hit.getSource();
            contacts.add(new Contact(
                    String.valueOf(sourceResult.get("name")),
                    String.valueOf(sourceResult.get("email")),
                    String.valueOf(sourceResult.get("phone"))
            ));
        }

        return contacts;
    }

    public Contact getContactByName(final String contactName) {
        final Contact contact = new Contact();
        SearchResponse response = elasticsearchComponent.getClient().prepareSearch(CONTACTS_INDEX)
                .setQuery(QueryBuilders.termQuery("_id", contactName))
                .setSize(100)
                .get();

        for(SearchHit hit : response.getHits()) {
            Map<String, Object> sourceResult = hit.getSource();
            contact.setName((String) sourceResult.get("name"));
            contact.setPhone((String) sourceResult.get("phone"));
            contact.setEmail((String) sourceResult.get("email"));
            break;
        }

        return contact;
    }
}
