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

public final class Publish {
    private final PublishRtmp rtmp;

    public static final String Type = "Publish";

    @JsonCreator
    protected Publish(@JsonProperty("rtmp") PublishRtmp rtmp) {
        this.rtmp = rtmp;
    }

    public PublishRtmp getRtmp() { return rtmp; }

    public String getType() { return Type; } 

    public Publish withRtmp(PublishRtmp rtmp) {
        return new Publish(Objects.requireNonNull(rtmp, "In class Publish the param 'rtmp' of type PublishRtmp can not be null"));
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
        if (!(o instanceof Publish)) return false;
        Publish other = (Publish) o;
        return Objects.equals(rtmp, other.rtmp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rtmp);
    }

    @Override
    public String toString() {
        return "Publish{rtmp=" + rtmp + '}';
    }
}