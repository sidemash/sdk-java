package com.sidemash.sdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public final class RestCollection<T> {
    private final String url;
    private final Pagination pagination;
    private final List<T> items;

    public static final String Type = "RestCollection";

    @JsonCreator
    protected RestCollection(@JsonProperty("url") String url,
                             @JsonProperty("pagination") Pagination pagination,
                             @JsonProperty("items") List<T> items) {
        this.url = url;
        this.pagination = pagination;
        this.items = items;
    }

    public String getUrl() { return url; }
    public Pagination getPagination() { return pagination; }
    public List<T> getItems() { return items; }

    public String getType() { return Type; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestCollection)) return false;
        RestCollection<?> other = (RestCollection<?>) o;
        return Objects.equals(url, other.url) &&
                Objects.equals(pagination, other.pagination) &&
                Objects.equals(items, other.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, pagination);
    }

    @Override
    public String toString() {
        return "RestCollection{url=" + url +
                              ", pagination=" + pagination +
                              ", items=" + items + '}';
    }
}