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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

public final class CreateStreamSquareForm {
    private final boolean isElastic;
    private final StreamSquare.Size size;
    private final Hook hook;
    private final Optional<String> description;
    private final Optional<String> foreignData;
    private final Optional<String> playDomainName;
    private final Optional<String> publishDomainName;

    public static final String Type = "CreateStreamSquareForm";

    @JsonCreator
    protected CreateStreamSquareForm(@JsonProperty("isElastic") boolean isElastic,
                                     @JsonProperty("size") StreamSquare.Size size,
                                     @JsonProperty("hook") Hook hook,
                                     @JsonProperty("description") Optional<String> description,
                                     @JsonProperty("foreignData") Optional<String> foreignData,
                                     @JsonProperty("playDomainName") Optional<String> playDomainName,
                                     @JsonProperty("publishDomainName") Optional<String> publishDomainName) {
        this.isElastic = isElastic;
        this.size = size;
        this.hook = hook;
        this.description = description;
        this.foreignData = foreignData;
        this.playDomainName = playDomainName;
        this.publishDomainName = publishDomainName;
    }

    public boolean isElastic() { return isElastic; }
    public StreamSquare.Size getSize() { return size; }
    public Hook getHook() { return hook; }
    public Optional<String> getDescription() { return description; }
    public Optional<String> getForeignData() { return foreignData; }
    public Optional<String> getPlayDomainName() { return playDomainName; }
    public Optional<String> getPublishDomainName() { return publishDomainName; }

    public String getType() { return Type; } 

    public CreateStreamSquareForm withIsElastic(boolean isElastic) {
        return new CreateStreamSquareForm(isElastic, size, hook, description, foreignData,
                                          playDomainName, publishDomainName);
    }

    public CreateStreamSquareForm withSize(StreamSquare.Size size) {
        return new CreateStreamSquareForm(isElastic, Objects.requireNonNull(size, "In class CreateStreamSquareForm the param 'size' of type StreamSquare.Size can not be null"),
                                          hook, description, foreignData, playDomainName,
                                          publishDomainName);
    }

    public CreateStreamSquareForm withHook(Hook hook) {
        return new CreateStreamSquareForm(isElastic, size, Objects.requireNonNull(hook, "In class CreateStreamSquareForm the param 'hook' of type Hook can not be null"),
                                          description, foreignData, playDomainName, publishDomainName);
    }

    public CreateStreamSquareForm withDescription(String description) {
        return new CreateStreamSquareForm(isElastic, size, hook, Optional.of(Objects.requireNonNull(description,
                                          "In class CreateStreamSquareForm the param 'description' of type Optional<String> can not be null")),
                                          foreignData, playDomainName, publishDomainName);
    }

    public CreateStreamSquareForm withForeignData(String foreignData) {
        return new CreateStreamSquareForm(isElastic, size, hook, description, Optional.of(Objects.requireNonNull(foreignData,
                                          "In class CreateStreamSquareForm the param 'foreignData' of type Optional<String> can not be null")),
                                          playDomainName, publishDomainName);
    }

    public CreateStreamSquareForm withPlayDomainName(String playDomainName) {
        return new CreateStreamSquareForm(isElastic, size, hook, description, foreignData,
                                          Optional.of(Objects.requireNonNull(playDomainName,
                                          "In class CreateStreamSquareForm the param 'playDomainName' of type Optional<String> can not be null")),
                                          publishDomainName);
    }

    public CreateStreamSquareForm withPublishDomainName(String publishDomainName) {
        return new CreateStreamSquareForm(isElastic, size, hook, description, foreignData,
                                          playDomainName, Optional.of(Objects.requireNonNull(publishDomainName,
                                          "In class CreateStreamSquareForm the param 'publishDomainName' of type Optional<String> can not be null")));
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
        if (!(o instanceof CreateStreamSquareForm)) return false;
        CreateStreamSquareForm other = (CreateStreamSquareForm) o;
        return isElastic == other.isElastic &&
               Objects.equals(size, other.size) &&
               Objects.equals(hook, other.hook) &&
               Objects.equals(description, other.description) &&
               Objects.equals(foreignData, other.foreignData) &&
               Objects.equals(playDomainName, other.playDomainName) &&
               Objects.equals(publishDomainName, other.publishDomainName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isElastic, size, hook, description,
                            foreignData, playDomainName, publishDomainName);
    }

    @Override
    public String toString() {
        return "CreateStreamSquareForm{isElastic=" + isElastic +
                                      ", size=" + size +
                                      ", hook=" + hook +
                                      ", description=" + description +
                                      ", foreignData=" + foreignData +
                                      ", playDomainName=" + playDomainName +
                                      ", publishDomainName=" + publishDomainName + '}';
    }
}