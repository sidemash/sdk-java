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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Hook.HttpCall.class, name = "Hook.HttpCall"),
    @JsonSubTypes.Type(value = Hook.WsCall.class, name = "Hook.WsCall"),
})
public abstract class Hook {
    protected Hook() {}

    public static final class HttpCall extends Hook {
        private final HttpMethod method;
        private final String url;

        public static final String Type = "Hook.HttpCall";

        @JsonCreator
        protected HttpCall(@JsonProperty("method") HttpMethod method,
                           @JsonProperty("url") String url) {
            this.method = method;
            this.url = url;
        }

        public HttpMethod getMethod() { return method; }
        public String getUrl() { return url; }

        public String getType() { return Type; } 

        public HttpCall withMethod(HttpMethod method) {
            return new HttpCall(Objects.requireNonNull(method, "In class HttpCall the param 'method' of type HttpMethod can not be null"),
                                url);
        }

        public HttpCall withUrl(String url) {
            return new HttpCall(method, Objects.requireNonNull(url, "In class HttpCall the param 'url' of type String can not be null"));
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
            if (!(o instanceof HttpCall)) return false;
            HttpCall other = (HttpCall) o;
            return Objects.equals(method, other.method) &&
                   Objects.equals(url, other.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(method, url);
        }

        @Override
        public String toString() {
            return "HttpCall{method=" + method +
                            ", url=" + url + '}';
        }
    }

    public static final class WsCall extends Hook {
        private final HttpMethod method;
        private final String url;

        public static final String Type = "Hook.WsCall";

        @JsonCreator
        protected WsCall(@JsonProperty("method") HttpMethod method,
                         @JsonProperty("url") String url) {
            this.method = method;
            this.url = url;
        }

        public HttpMethod getMethod() { return method; }
        public String getUrl() { return url; }

        public String getType() { return Type; } 

        public WsCall withMethod(HttpMethod method) {
            return new WsCall(Objects.requireNonNull(method, "In class WsCall the param 'method' of type HttpMethod can not be null"),
                              url);
        }

        public WsCall withUrl(String url) {
            return new WsCall(method, Objects.requireNonNull(url, "In class WsCall the param 'url' of type String can not be null"));
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
            if (!(o instanceof WsCall)) return false;
            WsCall other = (WsCall) o;
            return Objects.equals(method, other.method) &&
                   Objects.equals(url, other.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(method, url);
        }

        @Override
        public String toString() {
            return "WsCall{method=" + method +
                          ", url=" + url + '}';
        }
    }

    public abstract String getType(); 

    public static final class Cases<T> {
        public CaseHttpCall<T> whenHttpCall(Function<Hook, T> fn) {
            List<Expr.Case<Hook, T>> previousCases = new ArrayList<>();
            return new CaseHttpCall<>(previousCases, fn);
        }

        public SpecializedMatching<T> matching() {
            return new SpecializedMatching<>(new ArrayList<>());
        }
    }

    public static final class SpecializedMatching<T> extends Expr.Matching<Hook, T> {
        public SpecializedMatching(List<Expr.Case<Hook, T>> cases) {
            super(cases);
        }

        public SpecializedMatching<T> when(BooleanSupplier condition, Function<Hook, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(condition, fn, null, Hook::getType)); 
        }

        public SpecializedMatching<T> whenHttpCall(Expr.Guard and, Function<Hook, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, HttpCall.Type, Hook::getType));
        }

        public SpecializedMatching<T> whenHttpCall(Function<Hook, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, HttpCall.Type, Hook::getType)); 
        }

        public SpecializedMatching<T> whenWsCall(Expr.Guard and, Function<Hook, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, WsCall.Type, Hook::getType));
        }

        public SpecializedMatching<T> whenWsCall(Function<Hook, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, WsCall.Type, Hook::getType)); 
        }
    }

    public static final class CaseHttpCall<T> extends Expr.CaseValue<Hook, T> {
        public CaseHttpCall(List<Expr.Case<Hook, T>> previousCases, Function<Hook, T> fn) {
            super(previousCases, fn, HttpCall.Type, Hook::getType);
        }

        public Expr.ExhaustiveMatching<Hook, T> whenWsCall(Function<Hook, T> fn) {
            List<Expr.Case<Hook, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseWsCall<T> caseWsCall = new CaseWsCall<>(previousCases, fn);
            return caseWsCall.whenValue();
        }
    }

    public static final class CaseWsCall<T> extends Expr.CaseValue<Hook, T> {
        public CaseWsCall(List<Expr.Case<Hook, T>> previousCases, Function<Hook, T> fn) {
            super(previousCases, fn, WsCall.Type, Hook::getType);
        }
    }

    public static final Set<String> AllPossiblesTypes = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(HttpCall.Type, WsCall.Type)));

    public <T> T fold(Function<Cases<T>, Expr.ExhaustiveMatching<Hook, T>> casesFn) {
        return casesFn.apply(new Cases<>()).apply(this);
    }

    public boolean isNotHttpCall() {
        return !getType().equals(HttpCall.Type); 
    }

    public boolean isNotWsCall() {
        return !getType().equals(WsCall.Type); 
    }

    public boolean isHttpCall() {
        return getType().equals(HttpCall.Type); 
    }

    public boolean isWsCall() {
        return getType().equals(WsCall.Type); 
    }

    public Optional<HttpCall> asHttpCallOption() {
        return isHttpCall() ? Optional.of((HttpCall) this) : Optional.empty();
    }

    public Optional<WsCall> asWsCallOption() {
        return isWsCall() ? Optional.of((WsCall) this) : Optional.empty();
    }

    public void ifHttpCall(Consumer<HttpCall> fn) {
        if(isHttpCall()) fn.accept((HttpCall) this);
    }

    public void ifWsCall(Consumer<WsCall> fn) {
        if(isWsCall()) fn.accept((WsCall) this);
    }

    public void ifNotHttpCall(Consumer<Hook> fn) {
        if(isNotHttpCall()) fn.accept(this);
    }

    public void ifNotWsCall(Consumer<Hook> fn) {
        if(isNotWsCall()) fn.accept(this);
    }
}