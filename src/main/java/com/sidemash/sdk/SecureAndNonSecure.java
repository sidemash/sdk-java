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

public final class SecureAndNonSecure {
    private final String secure;
    private final String nonSecureOn80;
    private final String nonSecure;

    public static final String Type = "SecureAndNonSecure";

    @JsonCreator
    protected SecureAndNonSecure(@JsonProperty("secure") String secure,
                                 @JsonProperty("nonSecureOn80") String nonSecureOn80,
                                 @JsonProperty("nonSecure") String nonSecure) {
        this.secure = secure;
        this.nonSecureOn80 = nonSecureOn80;
        this.nonSecure = nonSecure;
    }

    public String getSecure() { return secure; }
    public String getNonSecureOn80() { return nonSecureOn80; }
    public String getNonSecure() { return nonSecure; }

    public String getType() { return Type; } 

    public SecureAndNonSecure withSecure(String secure) {
        return new SecureAndNonSecure(Objects.requireNonNull(secure, "In class SecureAndNonSecure the param 'secure' of type String can not be null"),
                                      nonSecureOn80, nonSecure);
    }

    public SecureAndNonSecure withNonSecureOn80(String nonSecureOn80) {
        return new SecureAndNonSecure(secure, Objects.requireNonNull(nonSecureOn80, "In class SecureAndNonSecure the param 'nonSecureOn80' of type String can not be null"),
                                      nonSecure);
    }

    public SecureAndNonSecure withNonSecure(String nonSecure) {
        return new SecureAndNonSecure(secure, nonSecureOn80, Objects.requireNonNull(nonSecure,
                                      "In class SecureAndNonSecure the param 'nonSecure' of type String can not be null"));
    }

    public String toJson() {
        return Utils.Js.toJson(this);
    }

    public JsonNode toJsonNode() {
        return Utils.Js.toJsonNode(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecureAndNonSecure)) return false;
        SecureAndNonSecure other = (SecureAndNonSecure) o;
        return Objects.equals(secure, other.secure) &&
               Objects.equals(nonSecureOn80, other.nonSecureOn80) &&
               Objects.equals(nonSecure, other.nonSecure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secure, nonSecureOn80, nonSecure);
    }

    @Override
    public String toString() {
        return "SecureAndNonSecure{secure=" + secure +
                                  ", nonSecureOn80=" + nonSecureOn80 +
                                  ", nonSecure=" + nonSecure + '}';
    }
}