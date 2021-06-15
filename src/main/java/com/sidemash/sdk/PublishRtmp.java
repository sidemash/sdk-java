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

public final class PublishRtmp {
    private final String streamKeyPrefix;
    private final SecureAndNonSecure ip;
    private final SecureAndNonSecure domain;

    public static final String Type = "PublishRtmp";

    @JsonCreator
    protected PublishRtmp(@JsonProperty("streamKeyPrefix") String streamKeyPrefix,
                          @JsonProperty("ip") SecureAndNonSecure ip,
                          @JsonProperty("domain") SecureAndNonSecure domain) {
        this.streamKeyPrefix = streamKeyPrefix;
        this.ip = ip;
        this.domain = domain;
    }

    public String getStreamKeyPrefix() { return streamKeyPrefix; }
    public SecureAndNonSecure getIp() { return ip; }
    public SecureAndNonSecure getDomain() { return domain; }

    public String getType() { return Type; } 

    public PublishRtmp withStreamKeyPrefix(String streamKeyPrefix) {
        return new PublishRtmp(Objects.requireNonNull(streamKeyPrefix, "In class PublishRtmp the param 'streamKeyPrefix' of type String can not be null"),
                               ip, domain);
    }

    public PublishRtmp withIp(SecureAndNonSecure ip) {
        return new PublishRtmp(streamKeyPrefix, Objects.requireNonNull(ip, "In class PublishRtmp the param 'ip' of type SecureAndNonSecure can not be null"),
                               domain);
    }

    public PublishRtmp withDomain(SecureAndNonSecure domain) {
        return new PublishRtmp(streamKeyPrefix, ip, Objects.requireNonNull(domain, "In class PublishRtmp the param 'domain' of type SecureAndNonSecure can not be null"));
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
        if (!(o instanceof PublishRtmp)) return false;
        PublishRtmp other = (PublishRtmp) o;
        return Objects.equals(streamKeyPrefix, other.streamKeyPrefix) &&
               Objects.equals(ip, other.ip) &&
               Objects.equals(domain, other.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamKeyPrefix, ip, domain);
    }

    @Override
    public String toString() {
        return "PublishRtmp{streamKeyPrefix=" + streamKeyPrefix +
                           ", ip=" + ip +
                           ", domain=" + domain + '}';
    }
}