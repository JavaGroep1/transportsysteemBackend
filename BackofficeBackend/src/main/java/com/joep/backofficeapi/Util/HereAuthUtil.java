package com.joep.backofficeapi.Util;

import com.here.account.auth.OAuth1ClientCredentialsProvider;
import com.here.account.http.apache.ApacheHttpClientProvider;
import com.here.account.oauth2.*;

public class HereAuthUtil {

    private static String tokenEndpointUrl = "https://account.api.here.com/oauth2/token";
    private static String accessKeyId = "mxNDghYJmeEDbBemQHcRzA";
    private static String accessKeySecret = "ot4OMxnUZVaQfMVvfO9vEDUk6kLTdiX8QteobgI8DqzWwQdoxdmEmHCnNq-OecjMJEmsPCQZzx9QGaa-Yea6-Q";

    public static String getToken() {
        ClientAuthorizationRequestProvider credentialsProvider = new OAuth1ClientCredentialsProvider(tokenEndpointUrl, accessKeyId, accessKeySecret);
        TokenEndpoint tokenEndpoint = HereAccount.getTokenEndpoint(
                ApacheHttpClientProvider.builder().build(),
                credentialsProvider);
        AccessTokenResponse fresh = tokenEndpoint.requestToken(new ClientCredentialsGrantRequest());
        return fresh.getAccessToken();
    }

//    public static HereAuth getAuthToken() throws IOException, InterruptedException {
////
////        TokenResponse response = new AuthorizationCodeTokenRequest(new HttpTransport(), new JsonFactory(), new GenericUrl() {
////        })
//
////       new AbstractOAuthGetToken("ff").OAuthParameters(consumer);
//
////        var token = new OAuthGetAccessToken().createParameters().sec
////        var accessToken = new AbstractOAuthGetToken().consumerKey("mxNDghYJmeEDbBemQHcRzA").
////        OAuthGetAccessToken.toPathParts()
//        int authSeconds = (int)(new Date().getTime()/1000);
//
//
//
//        HttpClient client = HttpClient.newHttpClient();
//        String post = "https://account.api.here.com/oauth2/token";
//        HttpRequest request = HttpRequest.newBuilder()
//                .header("Authorization", MessageFormat.format("OAuth " +
//                        "oauth_consumer_key=\"mxNDghYJmeEDbBemQHcRzA\"," +
//                        "oauth_signature_method=\"HMAC-SHA256\"," +
//                        "oauth_timestamp=\"{0}\"," +
//                        "oauth_nonce=\"LXeAlCq8wgo\"," +
//                        "oauth_version=\"1.0\"," +
//                        "oauth_signature=\"Uin%2BGxF2Hhurg27eIfUqzY2WqyVOlZiy5XKKP9mkW4c%3D\"", authSeconds))
//                .header("content-type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString("{\"grantType\" : \"client_credentials\"}"))
//                .uri(URI.create(post))
//                .build();
//
//        HttpResponse<String> response =
//                client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//        System.out.println(response.toString());
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.readValue(response.body(), HereAuth.class);
//        }
//        catch (Throwable e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
}
