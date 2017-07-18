package com.joaosakai.searchbar.global;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jgenari on 7/10/17.
 */
public class ElasticsearchClient  {

    private TransportClient client;

    private static ElasticsearchClient instance;

    private ElasticsearchClient(final TransportClient client) {
        this.client = client;
    }

    public static synchronized ElasticsearchClient getInstance() {
        if (instance == null) {
            final Settings settings = Settings.builder()
                    .put("cluster.name", "contacts-cluster")
                    .build();
            TransportClient client = null;
            try {
                client = new PreBuiltTransportClient(settings)
                        .addTransportAddress(new InetSocketTransportAddress(
                                InetAddress.getByName("18.231.19.206"), 9300));

            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            instance = new ElasticsearchClient(client);
        }

        return instance;
    }

    public Client getClient() {
        return client;
    }
}
