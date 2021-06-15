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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListForm {
    private final Optional<String> where;
    private final Optional<Integer> limit;
    private final Optional<String> orderBy;

    public static final String Type = "ListForm";

    @JsonCreator
    protected ListForm(@JsonProperty("where") Optional<String> where,
                       @JsonProperty("limit") Optional<Integer> limit,
                       @JsonProperty("orderBy") Optional<String> orderBy) {
        this.where = where;
        this.limit = limit;
        this.orderBy = orderBy;
    }

    public Optional<String> getWhere() { return where; }
    public Optional<Integer> getLimit() { return limit; }
    public Optional<String> getOrderBy() { return orderBy; }

    public String getType() { return Type; } 

    public ListForm withWhere(String where) {
        return new ListForm(Optional.of(Objects.requireNonNull(where, "In class ListForm the param 'where' of type Optional<String> can not be null")),
                            limit, orderBy);
    }

    public ListForm withLimit(int limit) {
        return new ListForm(where, Optional.of(Objects.requireNonNull(limit, "In class ListForm the param 'limit' of type Optional<Integer> can not be null")),
                            orderBy);
    }

    public ListForm withOrderBy(String orderBy) {
        return new ListForm(where, limit, Optional.of(Objects.requireNonNull(orderBy, "In class ListForm the param 'orderBy' of type Optional<String> can not be null")));
    }

    public String toJson() {
        return Utils.Js.toJson(this);
    }

    public JsonNode toJsonNode() {
        return Utils.Js.toJsonNode(this);
    }

    public String toQueryString() {
        return (
                Stream.of(where.map(w -> "where=" + w).orElse(""), limit.map(l -> "limit=" + l).orElse(""), orderBy.map(w -> "orderBy=" + w).orElse(""))
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.joining("&", "?", ""))
        );
    }

    public Optional<String> toQueryStringOption() {
        return Optional.of(toQueryString()).filter(s -> !s.isEmpty());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListForm)) return false;
        ListForm other = (ListForm) o;
        return Objects.equals(where, other.where) &&
               Objects.equals(limit, other.limit) &&
               Objects.equals(orderBy, other.orderBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(where, limit, orderBy);
    }

    @Override
    public String toString() {
        return "ListForm{where=" + where +
                        ", limit=" + limit +
                        ", orderBy=" + orderBy + '}';
    }
}