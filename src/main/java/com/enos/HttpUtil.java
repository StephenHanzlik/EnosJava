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

    public void doGet() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
//**********************************************************************************
//            Old Call: http://wcc.sc.egov.usda.gov/reportGenerator/view_csv/customSingleStationReport/daily/335:CO:SNTL%7Cid%3D%22%22%7Cname/2013-01-15,2013-01-18/WTEQ%3A%3Avalue%2CWTEQ%3A%3Adelta%2CSNWD%3A%3Avalue%2CSNWD%3A%3Adelta
//
//            WTEQ::value,WTEQ::delta,SNWD::value,SNWD::delta
//
//            Date: 2013-01-18
//            Snow Water Equivalent (in) Start of Day Values: 5.8
//            Change In Snow Water Equivalent (in): 0.0
//            Snow Depth (in) Start of Day Values: 28
//            Change In Snow Depth (in): -1
//
//
//            New Call: https://wcc.sc.egov.usda.gov/reportGenerator/view_csv/customMultiTimeSeriesGroupByStationReport/daily/start_of_period/335:CO:SNTL%7Cid=%22%22%7Cname/0,0/TAVG::value,TMAX::value,TMIN::value,PREC::value,PRCP::value,SNWD::value,TOBS::value?fitToScreen=false
//
//            TAVG::value,TMAX::value,TMIN::value,PREC::value,PRCP::value,SNWD::value,TOBS::value
//
//            Date: 2020-04-08
//            Air Temperature Average (degF): Null
//            Air Temperature Maximum (degF): Null
//            Air Temperature Minimum (degF): Null
//            Precipitation Accumulation (in) Start of Day Values: 23.8
//            Precipitation Increment (in): Null
//            Snow Depth (in) Start of Day Values: 64
//            Air Temperature Observed (degF) Start of Day Values: 29
// **********************************************************************************




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