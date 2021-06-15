package com.sidemash.sdk;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.sidemash.sdk.Sdk.Host;
import static com.sidemash.sdk.Sdk.Version;


final class Http {

    static <T> T Get(String path, Auth auth) {
        return Get(path, Collections.emptyMap(), Optional.empty(), auth);
    }
    static <T> T Get(String path, Map<String, String> headers, Optional<String> queryString, Auth auth){
        return call(path, "GET", headers, queryString, Optional.empty(), auth);
    }

    static <T> RestCollection<T> List(String path, Optional<String> queryString, Auth auth){
        return List(path, Collections.emptyMap(), queryString, auth);
    }

    static <T> RestCollection<T> List(String path, Map<String, String> headers, Optional<String> queryString, Auth auth){
        return call(path, "GET", headers, queryString, Optional.empty(), auth);
    }


    static <T> T Post(String path,  Optional<String> body, Auth auth) {
        return Post(path, Collections.emptyMap(), Optional.empty(), body, auth);
    }

    static <T> T Post(String path, Map<String, String> headers, Optional<String> queryString, Optional<String> body, Auth auth){
        return call(path, "POST", headers, queryString, body, auth);
    }

    static <T> T Patch(String path, Map<String, String> headers, Optional<String> queryString, Optional<String> body, Auth auth) {
        return call(path, "PATCH", headers, queryString, body, auth);
    }

    static <T> T Put(String path, Map<String, String> headers, Optional<String> queryString, Optional<String> body, Auth auth) {
        return call(path, "PUT", headers, queryString, body, auth);
    }

    static void Delete(String path, Map<String, String> headers, Optional<String> queryString, Optional<String> body, Auth auth) {
        call(path, "DELETE", headers, queryString, body, auth);
    }


    private static<T> T call(String path,
                             String method,
                             Map<String, String> headers,
                             Optional<String> queryString,
                             Optional<String> body,
                             Auth auth) {

        try {
            URL queryUrl = new URL(Host + path + (queryString.orElse("")));
            HttpURLConnection connection = (HttpURLConnection) queryUrl.openConnection();
            if(method.equals("PATCH")) {
                connection.setRequestMethod("POST");
            }
            else connection.setRequestMethod(method);


            Map<String, String> signedHeaders = computeSignedHeaders(method, body, headers, auth);
            if(!method.equals("GET")){
                SdmRequest request =
                        new SdmRequest(
                                System.currentTimeMillis(),
                                method,
                                path,
                                queryString,
                                signedHeaders,
                                body.map(Http::sha256));
                connection.setRequestProperty("X-Sdm-Nonce",  String.valueOf(request.getNonce()));
                connection.setRequestProperty("X-Sdm-SignedHeaders", String.join(",", signedHeaders.keySet()));
                connection.setRequestProperty("X-Sdm-Signature",  ("SHA256 " + sign(request.toMessage(), auth.getPrivateKey())));
            }
            signedHeaders.forEach(connection::setRequestProperty);
            if(body.isPresent()){
                connection.setDoOutput(true);
                connection.getOutputStream().write(body.get().getBytes(StandardCharsets.UTF_8));
            }

            int statusCode = connection.getResponseCode();
            String statusMessage = connection.getResponseMessage();
            if(statusCode < 300) {
                String responseBody = getResponseBody(connection.getInputStream());
                return Utils.Js.fromJson(responseBody);
            }
            else throw new CallException(statusCode, statusMessage, getResponseBody(connection.getErrorStream()));
        }
        catch (IOException | CallException e) {
            throw new RuntimeException(e);
        }
    }


    private static String getResponseBody(final InputStream stream) throws IOException {
        BufferedReader rd  = new BufferedReader( new InputStreamReader(stream));
        StringJoiner sj = new StringJoiner("\n");
        String line = rd.readLine();
        while(!Objects.isNull(line)){
            sj.add(line);
            line = rd.readLine();
        }
        return sj.toString();
    }


    private static String sign(final String message,
                               final String privateKey) {
        String Algorithm  = "HmacSHA256";
        SecretKeySpec secretKeySpec = new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), Algorithm);
        try {
            final Mac mac =  Mac.getInstance(Algorithm);
            mac.init(secretKeySpec);
            mac.update(message.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(mac.doFinal());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


    static String sha256(final String message)  {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(message.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> computeSignedHeaders(final String method,
                                                            final Optional<String> body,
                                                            final Map<String, String> headers,
                                                            final Auth auth)  {
        Map<String, String> signedHeaders = new HashMap<>();
        signedHeaders.put("Accept", "application/json");
        signedHeaders.put("User-Agent", "Sdk Java " + Version);
        signedHeaders.put("Authorization", "Bearer " + auth.getToken());
        signedHeaders.putAll(headers);
        body.ifPresent(b -> {
            signedHeaders.put("Content-Type", "application/json");
        });
        if(method.equals("PATCH")) {
            signedHeaders.put("X-HTTP-Method-Override", "PATCH");
        }
        return signedHeaders;
    }
}