package com.enos;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

//@WebServlet(
//        name = "stationservlet",
//        urlPatterns = "/station-data"
//)
public class HttpUtil extends HttpServlet {

        public void doGet(String[] arg) throws IOException {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            try {

                HttpGet request = new HttpGet("https://httpbin.org/get");

                // add request headers
                request.addHeader("custom-key", "mkyong");
                request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

                CloseableHttpResponse response = httpClient.execute(request);

                try {

                    // Get HttpResponse Status
                    System.out.println(response.getProtocolVersion());              // HTTP/1.1
                    System.out.println(response.getStatusLine().getStatusCode());   // 200
                    System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                    System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        // return it as a String
                        String result = EntityUtils.toString(entity);
                        System.out.println(result);
                    }

                } finally {
                    response.close();
                }
            } finally {
                httpClient.close();
            }
        }

}
