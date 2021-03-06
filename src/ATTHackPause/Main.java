/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package ATTHackPause;

import java.util.ArrayList;
import java.util.List;

import com.oracle.javafx.jmx.json.JSONReader;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Main {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("https://api-m2x.att.com/v2/devices/ecbcc13b43417c6d9e1dc7618bbb1e6c/streams/");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

            HttpPost httpPost = new HttpPost("https://api-m2x.att.com/v2/devices/ecbcc13b43417c6d9e1dc7618bbb1e6c/streams/Distance/values");
           // List <NameValuePair> nvps = new ArrayList <NameValuePair>();
           // nvps.add(new BasicNameValuePair("value", "15"));
           // nvps.add(new BasicNameValuePair("timestamp", "2016-04-09T19:37:42+00:00"));
           // httpPost.setEntity(new UrlEncodedFormEntity(nvps));


            String jsonString = "{\"values\":[{\"value\": \"99\",\"timestamp\": \"2016-04-09T20:43:49+00:00\"}]}";
            //System.out.println("\n\njsonString: " + jsonString);
//            JSONArray jsonArray = new JSONArray(jsonString);
//            System.out.println("\n\njsonArray: " + jsonArray);
            StringEntity se = new StringEntity(jsonString);
            httpPost.setEntity(se);

            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("X-M2X-KEY", "20bfa4a4f5ed88f94d772e8389e9a6d0");
            CloseableHttpResponse response2 = httpclient.execute(httpPost);



            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
