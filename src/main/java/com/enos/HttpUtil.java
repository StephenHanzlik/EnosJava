package com.enos;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class HttpUtil extends HttpServlet {

    public String doGet(String requestUri) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
//          https://httpbin.org/get
//          "http://wcc.sc.egov.usda.gov/reportGenerator/view_csv/customSingleStationReport/daily/335:CO:SNTL%7Cid%3D%22%22%7Cname/2013-01-15,2013-01-18/WTEQ%3A%3Avalue%2CWTEQ%3A%3Adelta%2CSNWD%3A%3Avalue%2CSNWD%3A%3Adelta"

            HttpGet request = new HttpGet(requestUri);

            // add request headers
//            request.addHeader("custom-key", "mkyong");
//            request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

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
                    return result;
                } else {
                    //Will want to add exception handling here
                    return "502";
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

}