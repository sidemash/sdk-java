package com.sidemash.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;

public class Expr {

    @FunctionalInterface
    public interface Guard extends BooleanSupplier {}

    public static <T> Predicate<T> oneOf(String head, String ...tail){
        return t -> {
           String s = t.toString();
           if(s.equals(head)) return true;
           for (String x : tail){
               if(s.equals(x)) return true;
           }
           return false;
        };
    }

    public static <T> Predicate<T> noneOf(String head, String ...tail){
        return t -> {
           String s = t.toString();
           if(s.equals(head)) return false;
           for (String x : tail){
               if(s.equals(x)) return false;
           }
           return true;
        };
    }

    public static Guard and(BooleanSupplier bs){
        return bs::getAsBoolean;
    }




    public static abstract class Case<A, T> {
        protected abstract boolean match(A value);
        protected abstract T eval(A value);
    }

    public abstract static class CaseValue<A, T> extends Case<A, T>{
        protected final List<Case<A, T>> previousCases;
        protected final Function<A, T> fn;
        protected final String value;
        protected final Function<A, String> valueToStringFn;


        public CaseValue(List<Case<A, T>> previousCases, Function<A, T> fn, String value) {
            this(previousCases, fn,value, A::toString);
        }

        public CaseValue(List<Case<A, T>> previousCases, Function<A, T> fn, String value, Function<A, String> valueToStringFn) {
            this.previousCases = previousCases;
            this.fn = fn;
            this.value = value;
            this.valueToStringFn = valueToStringFn;
        }

        public CaseValue(Function<A, T> fn) {
            this(new ArrayList<>(), fn, "", A::toString);
        }

        protected ExhaustiveMatching<A, T> whenValue() {
            List<Case<A, T>> cases = new ArrayList<>(previousCases);
            cases.add(this);
            return new ExhaustiveMatching<>(cases);
        }

        @Override protected boolean match(A value) { return this.valueToStringFn.apply(value).equals(this.value); }
        @Override protected T eval(A value) { return fn.apply(value); }
    }

    public static class ExhaustiveMatching<A, T> {
        private final List<Case<A, T>> cases;
        public ExhaustiveMatching(List<Case<A, T>> cases) {
            this.cases = cases;
        }
        protected T apply(A value) {
            for(Case<A, T> c : cases){
                if(c.match(value)) return c.eval(value);
            }
            throw new IllegalArgumentException("Invalid value " + value + " supplied for Exhaustive matching");
        }
    }

    public static class Matching<A, T> {
        protected final List<Case<A, T>> cases;
        public Matching(List<Case<A, T>> cases) {
            this.cases = cases;
        }

        protected List<Case<A, T>> allWhenConditionCases(BooleanSupplier guard, Function<A, T> fn, String value, Function<A, String> valueToStringFn) {
            Case<A, T> lastCase = new CaseValue<A, T>(new ArrayList<>(this.cases), fn, value, valueToStringFn){
                @Override
                public boolean match(A value) {
                    if(value == null) return guard.getAsBoolean();
                    else return guard.getAsBoolean() && super.match(value);
                }
            };
            List<Case<A, T>> allCases = new ArrayList<>(this.cases);
            allCases.add(lastCase);
            return allCases;
        }

        protected List<Case<A, T>> allWhenConditionCases(BooleanSupplier guard, Function<A, T> fn, String value) {
            return allWhenConditionCases(guard, fn, value, A::toString);
        }

        public ExhaustiveMatching<A, T> whatever(Function<A, T> fn) {
            List<Case<A, T>> allCases = new ArrayList<>(this.cases);
            Case<A, T> lastCase = new CaseValue<A, T>(fn){
                @Override
                public boolean match(Object value) {
                    return true;
                }
            };
            allCases.add(lastCase);
            return new ExhaustiveMatching<>(allCases);
        }
        public ExhaustiveMatching<A, T> otherwise(Function<A, T> fn) {
            return whatever(fn);
        }
    }




    /*
    // Generated
    public static class Cases<A, T> {
        public CaseS<A, T> whenS(Function<A, T> fn) {
            List<Case<A, T>> previousCases = new ArrayList<>();
            return new CaseS<>(previousCases, fn);
        }

        public Matching<A, T> matching() { return new Matching<>(new ArrayList<>()); }
    }

    public static class MatchingGenerated<A, T> extends Matching<A, T>{
        public MatchingGenerated(List<Case<A, T>> cases) {
            super(cases);
        }
        public Matching<A, T> whenL(Guard and, Function<A, T> fn) { return whenCondition(and, fn, "L"); }
        public Matching<A, T> whenS(Guard and, Function<A, T> fn) { return whenCondition(and, fn, "S"); }
    }


    public static class CaseS<A, T> extends CaseValue<A, T> {
        public CaseS(List<Case<A, T>> previousCases, Function<A, T> fn) {
            super(previousCases, fn, "S");
        }

        public ExhaustiveMatching<A, T> whenL(Function<A, T> fn) {
            CaseL<>
            return whenValue(fn);
        }
    }

    public static class CaseL<A, T> extends CaseValue<A, T> {
        public CaseL(List<Case<A, T>> previousCases, Function<A, T> fn) {
            super(previousCases, fn, "L");
        }

        public ExhaustiveMatching<A, T> whenL(Function<A, T> fn) {
            return whenValue(fn);
        }
    }
    */
}
