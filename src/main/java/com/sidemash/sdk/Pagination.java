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
import java.util.Optional;

public final class Pagination {
    private final String selfUrl;
    private final Optional<String> prevUrl;
    private final Optional<String> nextUrl;
    private final UTCDateTime startedTime;
    private final int nbItemsOnThisPage;
    private final int page;
    private final int nbItemsPerPage;

    public static final String Type = "Pagination";

    @JsonCreator
    protected Pagination(@JsonProperty("selfUrl") String selfUrl,
                         @JsonProperty("prevUrl") Optional<String> prevUrl,
                         @JsonProperty("nextUrl") Optional<String> nextUrl,
                         @JsonProperty("startedTime") UTCDateTime startedTime,
                         @JsonProperty("nbItemsOnThisPage") int nbItemsOnThisPage,
                         @JsonProperty("page") int page,
                         @JsonProperty("nbItemsPerPage") int nbItemsPerPage) {
        this.selfUrl = selfUrl;
        this.prevUrl = prevUrl;
        this.nextUrl = nextUrl;
        this.startedTime = startedTime;
        this.nbItemsOnThisPage = nbItemsOnThisPage;
        this.page = page;
        this.nbItemsPerPage = nbItemsPerPage;
    }

    public String getSelfUrl() { return selfUrl; }
    public Optional<String> getPrevUrl() { return prevUrl; }
    public Optional<String> getNextUrl() { return nextUrl; }
    public UTCDateTime getStartedTime() { return startedTime; }
    public int getNbItemsOnThisPage() { return nbItemsOnThisPage; }
    public int getPage() { return page; }
    public int getNbItemsPerPage() { return nbItemsPerPage; }

    public String getType() { return Type; } 

    public Pagination withSelfUrl(String selfUrl) {
        return new Pagination(Objects.requireNonNull(selfUrl, "In class Pagination the param 'selfUrl' of type String can not be null"),
                              prevUrl, nextUrl, startedTime, nbItemsOnThisPage, page, nbItemsPerPage);
    }

    public Pagination withPrevUrl(String prevUrl) {
        return new Pagination(selfUrl, Optional.of(Objects.requireNonNull(prevUrl, "In class Pagination the param 'prevUrl' of type Optional<String> can not be null")),
                              nextUrl, startedTime, nbItemsOnThisPage, page, nbItemsPerPage);
    }

    public Pagination withNextUrl(String nextUrl) {
        return new Pagination(selfUrl, prevUrl, Optional.of(Objects.requireNonNull(nextUrl,
                              "In class Pagination the param 'nextUrl' of type Optional<String> can not be null")),
                              startedTime, nbItemsOnThisPage, page, nbItemsPerPage);
    }

    public Pagination withStartedTime(UTCDateTime startedTime) {
        return new Pagination(selfUrl, prevUrl, nextUrl, Objects.requireNonNull(startedTime,
                              "In class Pagination the param 'startedTime' of type UTCDateTime can not be null"),
                              nbItemsOnThisPage, page, nbItemsPerPage);
    }

    public Pagination withNbItemsOnThisPage(int nbItemsOnThisPage) {
        return new Pagination(selfUrl, prevUrl, nextUrl, startedTime, nbItemsOnThisPage,
                              page, nbItemsPerPage);
    }

    public Pagination withPage(int page) {
        return new Pagination(selfUrl, prevUrl, nextUrl, startedTime, nbItemsOnThisPage,
                              page, nbItemsPerPage);
    }

    public Pagination withNbItemsPerPage(int nbItemsPerPage) {
        return new Pagination(selfUrl, prevUrl, nextUrl, startedTime, nbItemsOnThisPage,
                              page, nbItemsPerPage);
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
        if (!(o instanceof Pagination)) return false;
        Pagination other = (Pagination) o;
        return Objects.equals(selfUrl, other.selfUrl) &&
               Objects.equals(prevUrl, other.prevUrl) &&
               Objects.equals(nextUrl, other.nextUrl) &&
               Objects.equals(startedTime, other.startedTime) &&
               nbItemsOnThisPage == other.nbItemsOnThisPage &&
               page == other.page &&
               nbItemsPerPage == other.nbItemsPerPage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(selfUrl, prevUrl, nextUrl, startedTime,
                            nbItemsOnThisPage, page, nbItemsPerPage);
    }

    @Override
    public String toString() {
        return "Pagination{selfUrl=" + selfUrl +
                          ", prevUrl=" + prevUrl +
                          ", nextUrl=" + nextUrl +
                          ", startedTime=" + startedTime +
                          ", nbItemsOnThisPage=" + nbItemsOnThisPage +
                          ", page=" + page +
                          ", nbItemsPerPage=" + nbItemsPerPage + '}';
    }
}