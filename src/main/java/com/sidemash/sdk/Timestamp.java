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

public final class Timestamp {
    private final long seconds;

    public static final String Type = "Timestamp";

    @JsonCreator
    protected Timestamp(@JsonProperty("seconds") long seconds) {
        this.seconds = seconds;
    }

    public long getSeconds() { return seconds; }

    public String getType() { return Type; } 

    public Timestamp withSeconds(long seconds) {
        return new Timestamp(seconds);
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
        if (!(o instanceof Timestamp)) return false;
        Timestamp other = (Timestamp) o;
        return seconds == other.seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seconds);
    }

    @Override
    public String toString() {
        return "Timestamp{seconds=" + seconds + '}';
    }
}