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

public final class StreamSquare {
    private final String id;
    private final String url;
    private final InstanceStatus status;
    private final boolean isElastic;
    private final StreamSquare.Size size;
    private final Optional<String> playDomainName;
    private final Optional<String> publishDomainName;
    private final Publish publish;
    private final Hook hook;
    private final Optional<String> description;
    private final Optional<String> foreignData;

    public static final String Type = "StreamSquare";

    @JsonCreator
    protected StreamSquare(@JsonProperty("id") String id,
                           @JsonProperty("url") String url,
                           @JsonProperty("status") InstanceStatus status,
                           @JsonProperty("isElastic") boolean isElastic,
                           @JsonProperty("size") StreamSquare.Size size,
                           @JsonProperty("playDomainName") Optional<String> playDomainName,
                           @JsonProperty("publishDomainName") Optional<String> publishDomainName,
                           @JsonProperty("publish") Publish publish,
                           @JsonProperty("hook") Hook hook,
                           @JsonProperty("description") Optional<String> description,
                           @JsonProperty("foreignData") Optional<String> foreignData) {
        this.id = id;
        this.url = url;
        this.status = status;
        this.isElastic = isElastic;
        this.size = size;
        this.playDomainName = playDomainName;
        this.publishDomainName = publishDomainName;
        this.publish = publish;
        this.hook = hook;
        this.description = description;
        this.foreignData = foreignData;
    }

    public static class Size {
        private final String value;

        @JsonCreator
        protected Size(String value) {
            this.value = value;
        }

        public static final class Cases<T> {
            public CaseS<T> whenS(Function<Size, T> fn) {
                List<Expr.Case<Size, T>> previousCases = new ArrayList<>();
                return new CaseS<>(previousCases, fn);
            }

            public SpecializedMatching<T> matching() {
                return new SpecializedMatching<>(new ArrayList<>());
            }
        }

        public static final class SpecializedMatching<T> extends Expr.Matching<Size, T> {
            public SpecializedMatching(List<Expr.Case<Size, T>> cases) {
                super(cases);
            }

            public SpecializedMatching<T> when(BooleanSupplier condition, Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(condition, fn, null)); 
            }

            public SpecializedMatching<T> whenS(Expr.Guard and, Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(and, fn, "S"));
            }

            public SpecializedMatching<T> whenS(Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "S")); 
            }

            public SpecializedMatching<T> whenM(Expr.Guard and, Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(and, fn, "M"));
            }

            public SpecializedMatching<T> whenM(Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "M")); 
            }

            public SpecializedMatching<T> whenL(Expr.Guard and, Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(and, fn, "L"));
            }

            public SpecializedMatching<T> whenL(Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "L")); 
            }

            public SpecializedMatching<T> whenXl(Expr.Guard and, Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Xl"));
            }

            public SpecializedMatching<T> whenXl(Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Xl")); 
            }

            public SpecializedMatching<T> whenXxl(Expr.Guard and, Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Xxl"));
            }

            public SpecializedMatching<T> whenXxl(Function<Size, T> fn) {
                return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Xxl")); 
            }
        }

        public static final class CaseS<T> extends Expr.CaseValue<Size, T> {
            public CaseS(List<Expr.Case<Size, T>> previousCases, Function<Size, T> fn) {
                super(previousCases, fn, "S");
            }

            public CaseM<T> whenM(Function<Size, T> fn) {
                List<Expr.Case<Size, T>> previousCases = new ArrayList<>(this.previousCases);
                previousCases.add(this);
                CaseM<T> caseM = new CaseM<>(previousCases, fn);
                return caseM;
            }
        }

        public static final class CaseM<T> extends Expr.CaseValue<Size, T> {
            public CaseM(List<Expr.Case<Size, T>> previousCases, Function<Size, T> fn) {
                super(previousCases, fn, "M");
            }

            public CaseL<T> whenL(Function<Size, T> fn) {
                List<Expr.Case<Size, T>> previousCases = new ArrayList<>(this.previousCases);
                previousCases.add(this);
                CaseL<T> caseL = new CaseL<>(previousCases, fn);
                return caseL;
            }
        }

        public static final class CaseL<T> extends Expr.CaseValue<Size, T> {
            public CaseL(List<Expr.Case<Size, T>> previousCases, Function<Size, T> fn) {
                super(previousCases, fn, "L");
            }

            public CaseXl<T> whenXl(Function<Size, T> fn) {
                List<Expr.Case<Size, T>> previousCases = new ArrayList<>(this.previousCases);
                previousCases.add(this);
                CaseXl<T> caseXl = new CaseXl<>(previousCases, fn);
                return caseXl;
            }
        }

        public static final class CaseXl<T> extends Expr.CaseValue<Size, T> {
            public CaseXl(List<Expr.Case<Size, T>> previousCases, Function<Size, T> fn) {
                super(previousCases, fn, "Xl");
            }

            public Expr.ExhaustiveMatching<Size, T> whenXxl(Function<Size, T> fn) {
                List<Expr.Case<Size, T>> previousCases = new ArrayList<>(this.previousCases);
                previousCases.add(this);
                CaseXxl<T> caseXxl = new CaseXxl<>(previousCases, fn);
                return caseXxl.whenValue();
            }
        }

        public static final class CaseXxl<T> extends Expr.CaseValue<Size, T> {
            public CaseXxl(List<Expr.Case<Size, T>> previousCases, Function<Size, T> fn) {
                super(previousCases, fn, "Xxl");
            }
        }

        public static final class S extends Size {
            protected S() {
                super("S");
            }
        }

        public static final class M extends Size {
            protected M() {
                super("M");
            }
        }

        public static final class L extends Size {
            protected L() {
                super("L");
            }
        }

        public static final class Xl extends Size {
            protected Xl() {
                super("XL");
            }
        }

        public static final class Xxl extends Size {
            protected Xxl() {
                super("XXL");
            }
        }

        public static final S S = new Size.S();
        public static final M M = new Size.M();
        public static final L L = new Size.L();
        public static final Xl XL = new Size.Xl();
        public static final Xxl XXL = new Size.Xxl();

        public static final Set<String> AllPossiblesValues = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("S", "M", "L", "XL", "XXL")));

        public static Optional<StreamSquare.Size> fromString(String value) {
            switch (value) {
                case "S" : return Optional.of(S); 
                case "M" : return Optional.of(M); 
                case "L" : return Optional.of(L); 
                case "XL" : return Optional.of(XL); 
                case "XXL" : return Optional.of(XXL); 
                default : return Optional.empty();
            }
        }

        public static boolean isValid(String value) {
            return AllPossiblesValues.contains(value);
        }

        public static boolean isNotS(String value) {
            return !value.equals("S");
        }

        public static boolean isNotM(String value) {
            return !value.equals("M");
        }

        public static boolean isNotL(String value) {
            return !value.equals("L");
        }

        public static boolean isNotXl(String value) {
            return !value.equals("XL");
        }

        public static boolean isNotXxl(String value) {
            return !value.equals("XXL");
        }

        public static void ifNotS(String value, Consumer<Size> fn) {
             if(isNotS(value)) fn.accept(S);
        }

        public static void ifNotM(String value, Consumer<Size> fn) {
             if(isNotM(value)) fn.accept(M);
        }

        public static void ifNotL(String value, Consumer<Size> fn) {
             if(isNotL(value)) fn.accept(L);
        }

        public static void ifNotXl(String value, Consumer<Size> fn) {
             if(isNotXl(value)) fn.accept(XL);
        }

        public static void ifNotXxl(String value, Consumer<Size> fn) {
             if(isNotXxl(value)) fn.accept(XXL);
        }

        public static boolean isS(String value) {
            return value.equals("S");
        }

        public static boolean isM(String value) {
            return value.equals("M");
        }

        public static boolean isL(String value) {
            return value.equals("L");
        }

        public static boolean isXl(String value) {
            return value.equals("XL");
        }

        public static boolean isXxl(String value) {
            return value.equals("XXL");
        }

        public static Optional<S> asSOption(String value) {
             return isS(value) ? Optional.of(S) : Optional.empty();
        }

        public static Optional<M> asMOption(String value) {
             return isM(value) ? Optional.of(M) : Optional.empty();
        }

        public static Optional<L> asLOption(String value) {
             return isL(value) ? Optional.of(L) : Optional.empty();
        }

        public static Optional<Xl> asXlOption(String value) {
             return isXl(value) ? Optional.of(XL) : Optional.empty();
        }

        public static Optional<Xxl> asXxlOption(String value) {
             return isXxl(value) ? Optional.of(XXL) : Optional.empty();
        }

        public static void ifS(String value, Consumer<S> fn) {
             if(isS(value)) fn.accept(S);
        }

        public static void ifM(String value, Consumer<M> fn) {
             if(isM(value)) fn.accept(M);
        }

        public static void ifL(String value, Consumer<L> fn) {
             if(isL(value)) fn.accept(L);
        }

        public static void ifXl(String value, Consumer<Xl> fn) {
             if(isXl(value)) fn.accept(XL);
        }

        public static void ifXxl(String value, Consumer<Xxl> fn) {
             if(isXxl(value)) fn.accept(XXL);
        }

        public static Optional<StreamSquare.Size> asOneOf(String value, Size ...selection) {
            for (Size s : selection) {
                if(s.value.equals(value)) return Optional.of(s); 
            }
            return Optional.empty();
        }

        public static Consumer<Consumer<StreamSquare.Size>> ifOneOf(String value, Size ...selection) {
            for (Size s : selection) {
                if(s.value.equals(value)) return fn -> fn.accept(s);
            }
            return (ignored) -> {};
        }

        public static Consumer<Consumer<StreamSquare.Size>> ifNoneOf(String value, Size ...selection) {
            for (Size s : selection) {
                if(s.value.equals(value)) return (ignored) -> {};
            }
            return fn -> { fromString(value).ifPresent(fn); };
        }

        public <T> T fold(Function<Cases<T>, Expr.ExhaustiveMatching<Size, T>> casesFn) {
            return casesFn.apply(new Cases<>()).apply(this);
        }

        public boolean isValid() {
            return isValid(this.value);
        }

        public boolean isNotS() {
            return isNotS(this.value);
        }

        public boolean isNotM() {
            return isNotM(this.value);
        }

        public boolean isNotL() {
            return isNotL(this.value);
        }

        public boolean isNotXl() {
            return isNotXl(this.value);
        }

        public boolean isNotXxl() {
            return isNotXxl(this.value);
        }

        public void ifNotS(Consumer<Size> fn) {
            ifNotS(this.value, fn);
        }

        public void ifNotM(Consumer<Size> fn) {
            ifNotM(this.value, fn);
        }

        public void ifNotL(Consumer<Size> fn) {
            ifNotL(this.value, fn);
        }

        public void ifNotXl(Consumer<Size> fn) {
            ifNotXl(this.value, fn);
        }

        public void ifNotXxl(Consumer<Size> fn) {
            ifNotXxl(this.value, fn);
        }

        public boolean isS() {
            return isS(this.value);
        }

        public boolean isM() {
            return isM(this.value);
        }

        public boolean isL() {
            return isL(this.value);
        }

        public boolean isXl() {
            return isXl(this.value);
        }

        public boolean isXxl() {
            return isXxl(this.value);
        }

        public Optional<S> asSOption() {
             return asSOption(this.value);
        }

        public Optional<M> asMOption() {
             return asMOption(this.value);
        }

        public Optional<L> asLOption() {
             return asLOption(this.value);
        }

        public Optional<Xl> asXlOption() {
             return asXlOption(this.value);
        }

        public Optional<Xxl> asXxlOption() {
             return asXxlOption(this.value);
        }

        public void ifS(Consumer<S> fn) {
            ifS(this.value, fn);
        }

        public void ifM(Consumer<M> fn) {
            ifM(this.value, fn);
        }

        public void ifL(Consumer<L> fn) {
            ifL(this.value, fn);
        }

        public void ifXl(Consumer<Xl> fn) {
            ifXl(this.value, fn);
        }

        public void ifXxl(Consumer<Xxl> fn) {
            ifXxl(this.value, fn);
        }

        public Optional<StreamSquare.Size> asOneOf(Size ...selection) {
            for (Size s : selection) {
                if(s.value.equals(value)) return Optional.of(s); 
            }
            return Optional.empty();
        }

        public Consumer<Consumer<StreamSquare.Size>> ifOneOf(Size ...selection) {
            for (Size s : selection) {
                if(s.value.equals(value)) return fn -> fn.accept(this); 
            }
            return (ignored) -> {};
        }

        public Consumer<Consumer<StreamSquare.Size>> ifNoneOf(Size ...selection) {
            for (Size s : selection) {
                if(s.value.equals(value)) return (ignored) -> {};
            }
            return fn -> fn.accept(this);
        }

        public String getValue() { return value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Size)) return false;
            Size other = (Size) o;
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

    public String getId() { return id; }
    public String getUrl() { return url; }
    public InstanceStatus getStatus() { return status; }
    public boolean isElastic() { return isElastic; }
    public StreamSquare.Size getSize() { return size; }
    public Optional<String> getPlayDomainName() { return playDomainName; }
    public Optional<String> getPublishDomainName() { return publishDomainName; }
    public Publish getPublish() { return publish; }
    public Hook getHook() { return hook; }
    public Optional<String> getDescription() { return description; }
    public Optional<String> getForeignData() { return foreignData; }

    public String getType() { return Type; } 

    public StreamSquare withId(String id) {
        return new StreamSquare(Objects.requireNonNull(id, "In class StreamSquare the param 'id' of type String can not be null"),
                                url, status, isElastic, size, playDomainName, publishDomainName,
                                publish, hook, description, foreignData);
    }

    public StreamSquare withUrl(String url) {
        return new StreamSquare(id, Objects.requireNonNull(url, "In class StreamSquare the param 'url' of type String can not be null"),
                                status, isElastic, size, playDomainName, publishDomainName,
                                publish, hook, description, foreignData);
    }

    public StreamSquare withStatus(InstanceStatus status) {
        return new StreamSquare(id, url, Objects.requireNonNull(status, "In class StreamSquare the param 'status' of type InstanceStatus can not be null"),
                                isElastic, size, playDomainName, publishDomainName, publish,
                                hook, description, foreignData);
    }

    public StreamSquare withIsElastic(boolean isElastic) {
        return new StreamSquare(id, url, status, isElastic, size, playDomainName, publishDomainName,
                                publish, hook, description, foreignData);
    }

    public StreamSquare withSize(StreamSquare.Size size) {
        return new StreamSquare(id, url, status, isElastic, Objects.requireNonNull(size,
                                "In class StreamSquare the param 'size' of type StreamSquare.Size can not be null"),
                                playDomainName, publishDomainName, publish, hook, description,
                                foreignData);
    }

    public StreamSquare withPlayDomainName(String playDomainName) {
        return new StreamSquare(id, url, status, isElastic, size, Optional.of(Objects.requireNonNull(playDomainName,
                                "In class StreamSquare the param 'playDomainName' of type Optional<String> can not be null")),
                                publishDomainName, publish, hook, description, foreignData);
    }

    public StreamSquare withPublishDomainName(String publishDomainName) {
        return new StreamSquare(id, url, status, isElastic, size, playDomainName, Optional.of(Objects.requireNonNull(publishDomainName,
                                "In class StreamSquare the param 'publishDomainName' of type Optional<String> can not be null")),
                                publish, hook, description, foreignData);
    }

    public StreamSquare withPublish(Publish publish) {
        return new StreamSquare(id, url, status, isElastic, size, playDomainName, publishDomainName,
                                Objects.requireNonNull(publish, "In class StreamSquare the param 'publish' of type Publish can not be null"),
                                hook, description, foreignData);
    }

    public StreamSquare withHook(Hook hook) {
        return new StreamSquare(id, url, status, isElastic, size, playDomainName, publishDomainName,
                                publish, Objects.requireNonNull(hook, "In class StreamSquare the param 'hook' of type Hook can not be null"),
                                description, foreignData);
    }

    public StreamSquare withDescription(String description) {
        return new StreamSquare(id, url, status, isElastic, size, playDomainName, publishDomainName,
                                publish, hook, Optional.of(Objects.requireNonNull(description,
                                "In class StreamSquare the param 'description' of type Optional<String> can not be null")),
                                foreignData);
    }

    public StreamSquare withForeignData(String foreignData) {
        return new StreamSquare(id, url, status, isElastic, size, playDomainName, publishDomainName,
                                publish, hook, description, Optional.of(Objects.requireNonNull(foreignData,
                                "In class StreamSquare the param 'foreignData' of type Optional<String> can not be null")));
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
        if (!(o instanceof StreamSquare)) return false;
        StreamSquare other = (StreamSquare) o;
        return Objects.equals(id, other.id) &&
               Objects.equals(url, other.url) &&
               Objects.equals(status, other.status) &&
               isElastic == other.isElastic &&
               Objects.equals(size, other.size) &&
               Objects.equals(playDomainName, other.playDomainName) &&
               Objects.equals(publishDomainName, other.publishDomainName) &&
               Objects.equals(publish, other.publish) &&
               Objects.equals(hook, other.hook) &&
               Objects.equals(description, other.description) &&
               Objects.equals(foreignData, other.foreignData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, status, isElastic, size,
                            playDomainName, publishDomainName,
                            publish, hook, description, foreignData);
    }

    @Override
    public String toString() {
        return "StreamSquare{id=" + id +
                            ", url=" + url +
                            ", status=" + status +
                            ", isElastic=" + isElastic +
                            ", size=" + size +
                            ", playDomainName=" + playDomainName +
                            ", publishDomainName=" + publishDomainName +
                            ", publish=" + publish +
                            ", hook=" + hook +
                            ", description=" + description +
                            ", foreignData=" + foreignData + '}';
    }
}