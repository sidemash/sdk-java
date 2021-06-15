package com.sidemash.sdk;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

public final class ListFormOld {
    final Optional<String> where;
    final Optional<Integer> limit;
    final Optional<String> orderBy;
    final ZonedDateTime time = ZonedDateTime.now(ZoneOffset.UTC);

    public static class Builder {
        Optional<String> where = Optional.empty();
        Optional<Integer> limit = Optional.empty();
        Optional<String> orderBy = Optional.empty();
        protected Builder() {}
        protected Builder(String where, int limit, String orderBy) {
            this.where = Optional.ofNullable(where);
            this.limit = Optional.of(limit);
            this.orderBy = Optional.ofNullable(orderBy);
        }

        public Builder limit(final int l) { this.limit = Optional.of(l); return this;  }

        public Builder orderBy(final String o) { this.orderBy = Optional.ofNullable(o); return this;  }
        public Builder orderBy(final Optional<String> o) { this.orderBy = Optional.ofNullable(o).flatMap(identity()); return this;  }

        public Builder where(final Optional<String> w) { this.where = Optional.ofNullable(w).flatMap(identity()); return this;  }
        public Builder where(final String w) { this.where = Optional.ofNullable(w); return this;  }

        public ListFormOld build() { return new ListFormOld(this);  }
    }

    public static Builder limit(final int l){ return new Builder().limit(l); }
    public static Builder orderBy(final String o){ return new Builder().orderBy(o); }
    public static Builder where(final String w){ return new Builder().where(w); }


    private ListFormOld(Builder builder) {
        this(builder.where, builder.limit, builder.orderBy);
    }

    @JsonCreator
    private ListFormOld(@JsonProperty("where") Optional<String> where,
                        @JsonProperty("limit") Optional<Integer> limit,
                        @JsonProperty("orderBy") Optional<String> orderBy) {
        this.where = where;
        this.limit = limit;
        this.orderBy = orderBy;
    }

    public ZonedDateTime getTime() {
        return time;
    }
    public Optional<String> getWhere() {
        return where;
    }

    public Optional<Integer> getLimit() {
        return limit;
    }

    public Optional<String> getOrderBy() {
        return orderBy;
    }


    public static ListFormOld ListForm(Builder builder) {
        return new ListFormOld(builder);
    }

    public ListFormOld withWhere(final String where) {
        return new ListFormOld(Optional.ofNullable(where), limit, orderBy);
    }

    public ListFormOld withWhere(final Optional<String> where) {
        return new ListFormOld(Optional.ofNullable(where).flatMap(identity()), limit, orderBy);
    }

    public ListFormOld withEmptyWhere() {
        return new ListFormOld(Optional.empty(), limit, orderBy);
    }

    public ListFormOld withLimit(final int limit) {
        return new ListFormOld(where, Optional.of(limit), orderBy);
    }

    public ListFormOld withLimit(final Optional<Integer> limit) {
        return new ListFormOld(where, Optional.of(limit).flatMap(identity()), orderBy);
    }

    public ListFormOld withEmptyLimit() {
        return new ListFormOld(where, Optional.empty(), orderBy);
    }

    public ListFormOld withOrderBy(final String orderBy) {
        return new ListFormOld(where, limit, Optional.ofNullable(orderBy));
    }

    public ListFormOld withOrderBy(final Optional<String> orderBy) {
        return new ListFormOld(where, limit, Optional.ofNullable(orderBy).flatMap(identity()));
    }

    public ListFormOld withEmptyOrderBy() {
        return new ListFormOld(where, limit, Optional.empty());
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
        if (!(o instanceof ListFormOld)) return false;
        ListFormOld listForm = (ListFormOld) o;
        return where.equals(listForm.where) &&
                limit.equals(listForm.limit) &&
                orderBy.equals(listForm.orderBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(where, limit, orderBy);
    }

    @Override
    public String toString() {
        return "ListForm(where=" + where +
                        ", orderBy=" + orderBy +
                        ", limit=" + limit + ')';
    }
}

