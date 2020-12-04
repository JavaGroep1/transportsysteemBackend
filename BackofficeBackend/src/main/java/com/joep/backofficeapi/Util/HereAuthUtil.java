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
}
