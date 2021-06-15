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

public final class UTCDateTime {
    private final String iso8601;
    private final Timestamp timestamp;

    public static final String Type = "UTCDateTime";

    @JsonCreator
    protected UTCDateTime(@JsonProperty("iso8601") String iso8601,
                          @JsonProperty("timestamp") Timestamp timestamp) {
        this.iso8601 = iso8601;
        this.timestamp = timestamp;
    }

    public String getIso8601() { return iso8601; }
    public Timestamp getTimestamp() { return timestamp; }

    public String getType() { return Type; } 

    public UTCDateTime withIso8601(String iso8601) {
        return new UTCDateTime(Objects.requireNonNull(iso8601, "In class UTCDateTime the param 'iso8601' of type String can not be null"),
                               timestamp);
    }

    public UTCDateTime withTimestamp(Timestamp timestamp) {
        return new UTCDateTime(iso8601, Objects.requireNonNull(timestamp, "In class UTCDateTime the param 'timestamp' of type Timestamp can not be null"));
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
        if (!(o instanceof UTCDateTime)) return false;
        UTCDateTime other = (UTCDateTime) o;
        return Objects.equals(iso8601, other.iso8601) &&
               Objects.equals(timestamp, other.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iso8601, timestamp);
    }

    @Override
    public String toString() {
        return "UTCDateTime{iso8601=" + iso8601 +
                           ", timestamp=" + timestamp + '}';
    }
}