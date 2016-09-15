package com.selenium.test.utils;


import org.apache.http.client.methods.RequestBuilder;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class RestDeviceClient {

    public static String addPD() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target("http://192.168.66.228:8081/api/1.0/device");

        String input = "{\"mac\" : \"30:85:A9:B3:36:F4\",\"active\" : true,\"ip\" : \"192.168.66.111\", \"name\" : \"Test\",\"mac_vendor\" : \"Cisco\"}";

        Response postResponce;

        postResponce = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(input, MediaType.APPLICATION_JSON), Response.class);

        if (postResponce.getStatus() != 200) {
            String s = "Failed : HTTP error code : "
                    + postResponce.getStatus();
            System.out.println(s);
        }

        String result = postResponce.readEntity(String.class).substring(6, 7);
        System.out.println(result);
        return result;
    }

    public static void changeStatus(String id, String ip, String action) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget webTarget = client.target("http://192.168.66.228:8081/api/1.0/devices").path(id)
                .path(ip)
                .path(action);

        String input = "{}";

        Invocation.Builder invocationBuilder = webTarget.request();
        Response response = invocationBuilder.put(Entity.entity(input, MediaType.APPLICATION_JSON), Response.class);
    }

}
