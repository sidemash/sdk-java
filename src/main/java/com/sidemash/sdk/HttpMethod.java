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
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;

public class HttpMethod {
    private final String value;

    @JsonCreator
    protected HttpMethod(String value) {
        this.value = value;
    }

    public static final class Cases<T> {
        public CaseGet<T> whenGet(Function<HttpMethod, T> fn) {
            List<Expr.Case<HttpMethod, T>> previousCases = new ArrayList<>();
            return new CaseGet<>(previousCases, fn);
        }

        public SpecializedMatching<T> matching() {
            return new SpecializedMatching<>(new ArrayList<>());
        }
    }

    public static final class SpecializedMatching<T> extends Expr.Matching<HttpMethod, T> {
        public SpecializedMatching(List<Expr.Case<HttpMethod, T>> cases) {
            super(cases);
        }

        public SpecializedMatching<T> when(BooleanSupplier condition, Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(condition, fn, null)); 
        }

        public SpecializedMatching<T> whenGet(Expr.Guard and, Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Get"));
        }

        public SpecializedMatching<T> whenGet(Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Get")); 
        }

        public SpecializedMatching<T> whenPost(Expr.Guard and, Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Post"));
        }

        public SpecializedMatching<T> whenPost(Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Post")); 
        }

        public SpecializedMatching<T> whenPut(Expr.Guard and, Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Put"));
        }

        public SpecializedMatching<T> whenPut(Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Put")); 
        }

        public SpecializedMatching<T> whenDelete(Expr.Guard and, Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Delete"));
        }

        public SpecializedMatching<T> whenDelete(Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Delete")); 
        }

        public SpecializedMatching<T> whenPatch(Expr.Guard and, Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Patch"));
        }

        public SpecializedMatching<T> whenPatch(Function<HttpMethod, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Patch")); 
        }
    }

    public static final class CaseGet<T> extends Expr.CaseValue<HttpMethod, T> {
        public CaseGet(List<Expr.Case<HttpMethod, T>> previousCases, Function<HttpMethod, T> fn) {
            super(previousCases, fn, "Get");
        }

        public CasePost<T> whenPost(Function<HttpMethod, T> fn) {
            List<Expr.Case<HttpMethod, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CasePost<T> casePost = new CasePost<>(previousCases, fn);
            return casePost;
        }
    }

    public static final class CasePost<T> extends Expr.CaseValue<HttpMethod, T> {
        public CasePost(List<Expr.Case<HttpMethod, T>> previousCases, Function<HttpMethod, T> fn) {
            super(previousCases, fn, "Post");
        }

        public CasePut<T> whenPut(Function<HttpMethod, T> fn) {
            List<Expr.Case<HttpMethod, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CasePut<T> casePut = new CasePut<>(previousCases, fn);
            return casePut;
        }
    }

    public static final class CasePut<T> extends Expr.CaseValue<HttpMethod, T> {
        public CasePut(List<Expr.Case<HttpMethod, T>> previousCases, Function<HttpMethod, T> fn) {
            super(previousCases, fn, "Put");
        }

        public CaseDelete<T> whenDelete(Function<HttpMethod, T> fn) {
            List<Expr.Case<HttpMethod, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseDelete<T> caseDelete = new CaseDelete<>(previousCases, fn);
            return caseDelete;
        }
    }

    public static final class CaseDelete<T> extends Expr.CaseValue<HttpMethod, T> {
        public CaseDelete(List<Expr.Case<HttpMethod, T>> previousCases, Function<HttpMethod, T> fn) {
            super(previousCases, fn, "Delete");
        }

        public Expr.ExhaustiveMatching<HttpMethod, T> whenPatch(Function<HttpMethod, T> fn) {
            List<Expr.Case<HttpMethod, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CasePatch<T> casePatch = new CasePatch<>(previousCases, fn);
            return casePatch.whenValue();
        }
    }

    public static final class CasePatch<T> extends Expr.CaseValue<HttpMethod, T> {
        public CasePatch(List<Expr.Case<HttpMethod, T>> previousCases, Function<HttpMethod, T> fn) {
            super(previousCases, fn, "Patch");
        }
    }

    public static final class Get extends HttpMethod {
        protected Get() {
            super("GET");
        }
    }

    public static final class Post extends HttpMethod {
        protected Post() {
            super("POST");
        }
    }

    public static final class Put extends HttpMethod {
        protected Put() {
            super("PUT");
        }
    }

    public static final class Delete extends HttpMethod {
        protected Delete() {
            super("DELETE");
        }
    }

    public static final class Patch extends HttpMethod {
        protected Patch() {
            super("PATCH");
        }
    }

    public static final Get GET = new HttpMethod.Get();
    public static final Post POST = new HttpMethod.Post();
    public static final Put PUT = new HttpMethod.Put();
    public static final Delete DELETE = new HttpMethod.Delete();
    public static final Patch PATCH = new HttpMethod.Patch();

    public static final Set<String> AllPossiblesValues =
        Collections.unmodifiableSet(new HashSet<>(Arrays.asList("GET",
                                                                "POST",
                                                                "PUT",
                                                                "DELETE",
                                                                "PATCH")));

    public static Optional<HttpMethod> fromString(String value) {
        switch (value) {
            case "GET" : return Optional.of(GET); 
            case "POST" : return Optional.of(POST); 
            case "PUT" : return Optional.of(PUT); 
            case "DELETE" : return Optional.of(DELETE); 
            case "PATCH" : return Optional.of(PATCH); 
            default : return Optional.empty();
        }
    }

    public static boolean isValid(String value) {
        return AllPossiblesValues.contains(value);
    }

    public static boolean isNotGet(String value) {
        return !value.equals("GET");
    }

    public static boolean isNotPost(String value) {
        return !value.equals("POST");
    }

    public static boolean isNotPut(String value) {
        return !value.equals("PUT");
    }

    public static boolean isNotDelete(String value) {
        return !value.equals("DELETE");
    }

    public static boolean isNotPatch(String value) {
        return !value.equals("PATCH");
    }

    public static void ifNotGet(String value, Consumer<HttpMethod> fn) {
         if(isNotGet(value)) fn.accept(GET);
    }

    public static void ifNotPost(String value, Consumer<HttpMethod> fn) {
         if(isNotPost(value)) fn.accept(POST);
    }

    public static void ifNotPut(String value, Consumer<HttpMethod> fn) {
         if(isNotPut(value)) fn.accept(PUT);
    }

    public static void ifNotDelete(String value, Consumer<HttpMethod> fn) {
         if(isNotDelete(value)) fn.accept(DELETE);
    }

    public static void ifNotPatch(String value, Consumer<HttpMethod> fn) {
         if(isNotPatch(value)) fn.accept(PATCH);
    }

    public static boolean isGet(String value) {
        return value.equals("GET");
    }

    public static boolean isPost(String value) {
        return value.equals("POST");
    }

    public static boolean isPut(String value) {
        return value.equals("PUT");
    }

    public static boolean isDelete(String value) {
        return value.equals("DELETE");
    }

    public static boolean isPatch(String value) {
        return value.equals("PATCH");
    }

    public static Optional<Get> asGetOption(String value) {
         return isGet(value) ? Optional.of(GET) : Optional.empty();
    }

    public static Optional<Post> asPostOption(String value) {
         return isPost(value) ? Optional.of(POST) : Optional.empty();
    }

    public static Optional<Put> asPutOption(String value) {
         return isPut(value) ? Optional.of(PUT) : Optional.empty();
    }

    public static Optional<Delete> asDeleteOption(String value) {
         return isDelete(value) ? Optional.of(DELETE) : Optional.empty();
    }

    public static Optional<Patch> asPatchOption(String value) {
         return isPatch(value) ? Optional.of(PATCH) : Optional.empty();
    }

    public static void ifGet(String value, Consumer<Get> fn) {
         if(isGet(value)) fn.accept(GET);
    }

    public static void ifPost(String value, Consumer<Post> fn) {
         if(isPost(value)) fn.accept(POST);
    }

    public static void ifPut(String value, Consumer<Put> fn) {
         if(isPut(value)) fn.accept(PUT);
    }

    public static void ifDelete(String value, Consumer<Delete> fn) {
         if(isDelete(value)) fn.accept(DELETE);
    }

    public static void ifPatch(String value, Consumer<Patch> fn) {
         if(isPatch(value)) fn.accept(PATCH);
    }

    public static Optional<HttpMethod> asOneOf(String value, HttpMethod ...selection) {
        for (HttpMethod s : selection) {
            if(s.value.equals(value)) return Optional.of(s); 
        }
        return Optional.empty();
    }

    public static Consumer<Consumer<HttpMethod>> ifOneOf(String value, HttpMethod ...selection) {
        for (HttpMethod s : selection) {
            if(s.value.equals(value)) return fn -> fn.accept(s);
        }
        return (ignored) -> {};
    }

    public static Consumer<Consumer<HttpMethod>> ifNoneOf(String value, HttpMethod ...selection) {
        for (HttpMethod s : selection) {
            if(s.value.equals(value)) return (ignored) -> {};
        }
        return fn -> { fromString(value).ifPresent(fn); };
    }

    public <T> T fold(Function<Cases<T>, Expr.ExhaustiveMatching<HttpMethod, T>> casesFn) {
        return casesFn.apply(new Cases<>()).apply(this);
    }

    public boolean isValid() {
        return isValid(this.value);
    }

    public boolean isNotGet() {
        return isNotGet(this.value);
    }

    public boolean isNotPost() {
        return isNotPost(this.value);
    }

    public boolean isNotPut() {
        return isNotPut(this.value);
    }

    public boolean isNotDelete() {
        return isNotDelete(this.value);
    }

    public boolean isNotPatch() {
        return isNotPatch(this.value);
    }

    public void ifNotGet(Consumer<HttpMethod> fn) {
        ifNotGet(this.value, fn);
    }

    public void ifNotPost(Consumer<HttpMethod> fn) {
        ifNotPost(this.value, fn);
    }

    public void ifNotPut(Consumer<HttpMethod> fn) {
        ifNotPut(this.value, fn);
    }

    public void ifNotDelete(Consumer<HttpMethod> fn) {
        ifNotDelete(this.value, fn);
    }

    public void ifNotPatch(Consumer<HttpMethod> fn) {
        ifNotPatch(this.value, fn);
    }

    public boolean isGet() {
        return isGet(this.value);
    }

    public boolean isPost() {
        return isPost(this.value);
    }

    public boolean isPut() {
        return isPut(this.value);
    }

    public boolean isDelete() {
        return isDelete(this.value);
    }

    public boolean isPatch() {
        return isPatch(this.value);
    }

    public Optional<Get> asGetOption() {
         return asGetOption(this.value);
    }

    public Optional<Post> asPostOption() {
         return asPostOption(this.value);
    }

    public Optional<Put> asPutOption() {
         return asPutOption(this.value);
    }

    public Optional<Delete> asDeleteOption() {
         return asDeleteOption(this.value);
    }

    public Optional<Patch> asPatchOption() {
         return asPatchOption(this.value);
    }

    public void ifGet(Consumer<Get> fn) {
        ifGet(this.value, fn);
    }

    public void ifPost(Consumer<Post> fn) {
        ifPost(this.value, fn);
    }

    public void ifPut(Consumer<Put> fn) {
        ifPut(this.value, fn);
    }

    public void ifDelete(Consumer<Delete> fn) {
        ifDelete(this.value, fn);
    }

    public void ifPatch(Consumer<Patch> fn) {
        ifPatch(this.value, fn);
    }

    public Optional<HttpMethod> asOneOf(HttpMethod ...selection) {
        for (HttpMethod s : selection) {
            if(s.value.equals(value)) return Optional.of(s); 
        }
        return Optional.empty();
    }

    public Consumer<Consumer<HttpMethod>> ifOneOf(HttpMethod ...selection) {
        for (HttpMethod s : selection) {
            if(s.value.equals(value)) return fn -> fn.accept(this); 
        }
        return (ignored) -> {};
    }

    public Consumer<Consumer<HttpMethod>> ifNoneOf(HttpMethod ...selection) {
        for (HttpMethod s : selection) {
            if(s.value.equals(value)) return (ignored) -> {};
        }
        return fn -> fn.accept(this);
    }

    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpMethod)) return false;
        HttpMethod other = (HttpMethod) o;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    @JsonValue
    public String toString() {
        return value;
    }
}