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
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import static com.sidemash.sdk.Sdk.Version;

public final class StreamSquareService {
    private final Auth auth;

    @JsonCreator
    protected StreamSquareService(@JsonProperty("auth") Auth auth) {
        this.auth = auth;
    }

    public StreamSquare create(CreateStreamSquareForm form) {
        return Http.Post("/" + Version + "/stream-squares", Collections.emptyMap(), Optional.empty(), Optional.of(form.toJson()), auth);
    }

    public StreamSquare get(String id) {
        return Http.Get("/" + Version + "/stream-squares/" + id, auth);
    }

    public RestCollection<StreamSquare> list() {
        return Http.List("/" + Version + "/stream-squares", Optional.empty(), auth);
    }

    public RestCollection<StreamSquare> list(ListForm form) {
        return Http.List("/" + Version + "/stream-squares", form.toQueryStringOption(), auth);
    }

    public StreamSquare update(UpdateStreamSquareForm form) {
        return Http.Patch("/" + Version + "/stream-squares/" + form.getId(), Collections.emptyMap(), Optional.empty(), Optional.of(form.toJson()), auth);
    }

    public void delete(String id) {
        Http.Delete("/" + Version + "/stream-squares/" + id, Collections.emptyMap(), Optional.empty(), Optional.empty(), auth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StreamSquareService)) return false;
        StreamSquareService other = (StreamSquareService) o;
        return Objects.equals(auth, other.auth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auth);
    }

    @Override
    public String toString() {
        return "StreamSquareService{auth=" + auth + '}';
    }
}