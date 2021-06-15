package com.sidemash.sdk;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class SdmRequest {
    private final long nonce;
    private final String method;
    private final String path;
    private final Optional<String> queryString;
    private final Map<String, String> signedHeaders;
    private final Optional<String> bodyHash;

    protected SdmRequest(long nonce,
                      String method,
                      String path,
                      Optional<String> queryString,
                      Map<String, String> signedHeaders,
                      Optional<String> bodyHash) {
        this.nonce = nonce;
        this.method = method;
        this.path = path;
        this.queryString = queryString;
        this.signedHeaders = signedHeaders;
        this.bodyHash = bodyHash;
    }

    public long getNonce() {
        return nonce;
    }

    public String toMessage(){
       return (nonce +
                signedHeaders.entrySet().stream().map(entry -> entry.getKey()  + ":" + entry.getValue()).collect(Collectors.joining("")) +
                method +
                path +
                queryString.orElse("") +
                bodyHash.orElse("")
        );
    }
}
