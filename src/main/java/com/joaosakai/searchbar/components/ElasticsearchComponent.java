package com.joaosakai.searchbar.components;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jgenari on 7/18/17.
 */
@Component
public class ElasticsearchComponent {

    static Logger logger = LoggerFactory.getLogger(ElasticsearchComponent.class);

    private TransportClient client;

    @Autowired
    public ElasticsearchComponent() {
        final Settings settings = Settings.builder()
                .put("cluster.name", "contacts-cluster")
                .build();
        try {
            this.client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(
                            InetAddress.getByName("18.231.19.206"), 9300));

        } catch (UnknownHostException e) {
            logger.error("Error on Elasticsearch Connection: ", e);
        }
    }

    public Client getClient() {
        return client;
    }


}
