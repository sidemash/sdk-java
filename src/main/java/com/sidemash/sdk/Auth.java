/*
   Copyright © 2020 Sidemash Cloud Services

   Licensed under the Apache  License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless  required  by  applicable  law  or  agreed to in writing,
   software  distributed  under  the  License  is distributed on an
   "AS IS"  BASIS, WITHOUT  WARRANTIES  OR CONDITIONS OF  ANY KIND,
   either  express  or  implied.  See the License for the  specific
   language governing permissions and limitations under the License.
*/


package com.sidemash.sdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Objects;

public final class Auth {
    private final String token;
    private final String secretKey;

    public static final String Type = "Auth";

    @JsonCreator
    protected Auth(@JsonProperty("token") String token,
                   @JsonProperty("secretKey") String secretKey) {
        this.token = token;
        this.secretKey = secretKey;
    }

    public String getToken() { return token; }
    public String getSecretKey() { return secretKey; }

    public String getType() { return Type; } 

    public Auth withToken(String token) {
        return new Auth(Objects.requireNonNull(token, "In class Auth the param 'token' of type String can not be null"),
                        secretKey);
    }

    public Auth withSecretKey(String secretKey) {
        return new Auth(token, Objects.requireNonNull(secretKey, "In class Auth the param 'secretKey' of type String can not be null"));
    }

    public String toJson() {
        return Utils.Js.toJson(this);
    }

    public JsonNode toJsonNode() {
        return Utils.Js.toJsonNode(this);
    }

    public String getPrivateKey() { return "FAKE_PRIVATE_KEY";}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auth)) return false;
        Auth other = (Auth) o;
        return Objects.equals(token, other.token) &&
               Objects.equals(secretKey, other.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, secretKey);
    }

    @Override
    public String toString() {
        return "Auth{token=" + token +
                    ", secretKey=******" + '}';
    }
}