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

public class InstanceStatus {
    private final String value;

    @JsonCreator
    protected InstanceStatus(String value) {
        this.value = value;
    }

    public static final class Cases<T> {
        public CaseInitializing<T> whenInitializing(Function<InstanceStatus, T> fn) {
            List<Expr.Case<InstanceStatus, T>> previousCases = new ArrayList<>();
            return new CaseInitializing<>(previousCases, fn);
        }

        public SpecializedMatching<T> matching() {
            return new SpecializedMatching<>(new ArrayList<>());
        }
    }

    public static final class SpecializedMatching<T> extends Expr.Matching<InstanceStatus, T> {
        public SpecializedMatching(List<Expr.Case<InstanceStatus, T>> cases) {
            super(cases);
        }

        public SpecializedMatching<T> when(BooleanSupplier condition, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(condition, fn, null)); 
        }

        public SpecializedMatching<T> whenInitializing(Expr.Guard and, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Initializing"));
        }

        public SpecializedMatching<T> whenInitializing(Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Initializing")); 
        }

        public SpecializedMatching<T> whenRunning(Expr.Guard and, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Running"));
        }

        public SpecializedMatching<T> whenRunning(Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Running")); 
        }

        public SpecializedMatching<T> whenRestarting(Expr.Guard and, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Restarting"));
        }

        public SpecializedMatching<T> whenRestarting(Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Restarting")); 
        }

        public SpecializedMatching<T> whenUpdating(Expr.Guard and, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Updating"));
        }

        public SpecializedMatching<T> whenUpdating(Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Updating")); 
        }

        public SpecializedMatching<T> whenMaintaining(Expr.Guard and, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Maintaining"));
        }

        public SpecializedMatching<T> whenMaintaining(Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Maintaining")); 
        }

        public SpecializedMatching<T> whenPartiallyAvailable(Expr.Guard and, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "PartiallyAvailable"));
        }

        public SpecializedMatching<T> whenPartiallyAvailable(Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "PartiallyAvailable")); 
        }

        public SpecializedMatching<T> whenNotAvailable(Expr.Guard and, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "NotAvailable"));
        }

        public SpecializedMatching<T> whenNotAvailable(Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "NotAvailable")); 
        }

        public SpecializedMatching<T> whenTerminating(Expr.Guard and, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Terminating"));
        }

        public SpecializedMatching<T> whenTerminating(Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Terminating")); 
        }

        public SpecializedMatching<T> whenTerminated(Expr.Guard and, Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(and, fn, "Terminated"));
        }

        public SpecializedMatching<T> whenTerminated(Function<InstanceStatus, T> fn) {
            return new SpecializedMatching<>(allWhenConditionCases(() -> true, fn, "Terminated")); 
        }
    }

    public static final class CaseInitializing<T> extends Expr.CaseValue<InstanceStatus, T> {
        public CaseInitializing(List<Expr.Case<InstanceStatus, T>> previousCases, Function<InstanceStatus, T> fn) {
            super(previousCases, fn, "Initializing");
        }

        public CaseRunning<T> whenRunning(Function<InstanceStatus, T> fn) {
            List<Expr.Case<InstanceStatus, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseRunning<T> caseRunning = new CaseRunning<>(previousCases, fn);
            return caseRunning;
        }
    }

    public static final class CaseRunning<T> extends Expr.CaseValue<InstanceStatus, T> {
        public CaseRunning(List<Expr.Case<InstanceStatus, T>> previousCases, Function<InstanceStatus, T> fn) {
            super(previousCases, fn, "Running");
        }

        public CaseRestarting<T> whenRestarting(Function<InstanceStatus, T> fn) {
            List<Expr.Case<InstanceStatus, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseRestarting<T> caseRestarting = new CaseRestarting<>(previousCases, fn);
            return caseRestarting;
        }
    }

    public static final class CaseRestarting<T> extends Expr.CaseValue<InstanceStatus, T> {
        public CaseRestarting(List<Expr.Case<InstanceStatus, T>> previousCases, Function<InstanceStatus, T> fn) {
            super(previousCases, fn, "Restarting");
        }

        public CaseUpdating<T> whenUpdating(Function<InstanceStatus, T> fn) {
            List<Expr.Case<InstanceStatus, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseUpdating<T> caseUpdating = new CaseUpdating<>(previousCases, fn);
            return caseUpdating;
        }
    }

    public static final class CaseUpdating<T> extends Expr.CaseValue<InstanceStatus, T> {
        public CaseUpdating(List<Expr.Case<InstanceStatus, T>> previousCases, Function<InstanceStatus, T> fn) {
            super(previousCases, fn, "Updating");
        }

        public CaseMaintaining<T> whenMaintaining(Function<InstanceStatus, T> fn) {
            List<Expr.Case<InstanceStatus, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseMaintaining<T> caseMaintaining = new CaseMaintaining<>(previousCases, fn);
            return caseMaintaining;
        }
    }

    public static final class CaseMaintaining<T> extends Expr.CaseValue<InstanceStatus, T> {
        public CaseMaintaining(List<Expr.Case<InstanceStatus, T>> previousCases, Function<InstanceStatus, T> fn) {
            super(previousCases, fn, "Maintaining");
        }

        public CasePartiallyAvailable<T> whenPartiallyAvailable(Function<InstanceStatus, T> fn) {
            List<Expr.Case<InstanceStatus, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CasePartiallyAvailable<T> casePartiallyAvailable = new CasePartiallyAvailable<>(previousCases, fn);
            return casePartiallyAvailable;
        }
    }

    public static final class CasePartiallyAvailable<T> extends Expr.CaseValue<InstanceStatus, T> {
        public CasePartiallyAvailable(List<Expr.Case<InstanceStatus, T>> previousCases, Function<InstanceStatus, T> fn) {
            super(previousCases, fn, "PartiallyAvailable");
        }

        public CaseNotAvailable<T> whenNotAvailable(Function<InstanceStatus, T> fn) {
            List<Expr.Case<InstanceStatus, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseNotAvailable<T> caseNotAvailable = new CaseNotAvailable<>(previousCases, fn);
            return caseNotAvailable;
        }
    }

    public static final class CaseNotAvailable<T> extends Expr.CaseValue<InstanceStatus, T> {
        public CaseNotAvailable(List<Expr.Case<InstanceStatus, T>> previousCases, Function<InstanceStatus, T> fn) {
            super(previousCases, fn, "NotAvailable");
        }

        public CaseTerminating<T> whenTerminating(Function<InstanceStatus, T> fn) {
            List<Expr.Case<InstanceStatus, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseTerminating<T> caseTerminating = new CaseTerminating<>(previousCases, fn);
            return caseTerminating;
        }
    }

    public static final class CaseTerminating<T> extends Expr.CaseValue<InstanceStatus, T> {
        public CaseTerminating(List<Expr.Case<InstanceStatus, T>> previousCases, Function<InstanceStatus, T> fn) {
            super(previousCases, fn, "Terminating");
        }

        public Expr.ExhaustiveMatching<InstanceStatus, T> whenTerminated(Function<InstanceStatus, T> fn) {
            List<Expr.Case<InstanceStatus, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseTerminated<T> caseTerminated = new CaseTerminated<>(previousCases, fn);
            return caseTerminated.whenValue();
        }
    }

    public static final class CaseTerminated<T> extends Expr.CaseValue<InstanceStatus, T> {
        public CaseTerminated(List<Expr.Case<InstanceStatus, T>> previousCases, Function<InstanceStatus, T> fn) {
            super(previousCases, fn, "Terminated");
        }
    }

    public static final class Initializing extends InstanceStatus {
        protected Initializing() {
            super("Initializing");
        }
    }

    public static final class Running extends InstanceStatus {
        protected Running() {
            super("Running");
        }
    }

    public static final class Restarting extends InstanceStatus {
        protected Restarting() {
            super("Restarting");
        }
    }

    public static final class Updating extends InstanceStatus {
        protected Updating() {
            super("Updating");
        }
    }

    public static final class Maintaining extends InstanceStatus {
        protected Maintaining() {
            super("Maintaining");
        }
    }

    public static final class PartiallyAvailable extends InstanceStatus {
        protected PartiallyAvailable() {
            super("PartiallyAvailable");
        }
    }

    public static final class NotAvailable extends InstanceStatus {
        protected NotAvailable() {
            super("NotAvailable");
        }
    }

    public static final class Terminating extends InstanceStatus {
        protected Terminating() {
            super("Terminating");
        }
    }

    public static final class Terminated extends InstanceStatus {
        protected Terminated() {
            super("Terminated");
        }
    }

    public static final Initializing Initializing = new InstanceStatus.Initializing();
    public static final Running Running = new InstanceStatus.Running();
    public static final Restarting Restarting = new InstanceStatus.Restarting();
    public static final Updating Updating = new InstanceStatus.Updating();
    public static final Maintaining Maintaining = new InstanceStatus.Maintaining();
    public static final PartiallyAvailable PartiallyAvailable = new InstanceStatus.PartiallyAvailable();
    public static final NotAvailable NotAvailable = new InstanceStatus.NotAvailable();
    public static final Terminating Terminating = new InstanceStatus.Terminating();
    public static final Terminated Terminated = new InstanceStatus.Terminated();

    public static final Set<String> AllPossiblesValues =
        Collections.unmodifiableSet(new HashSet<>(Arrays.asList("Initializing",
                                                                "Running",
                                                                "Restarting",
                                                                "Updating",
                                                                "Maintaining",
                                                                "PartiallyAvailable",
                                                                "NotAvailable",
                                                                "Terminating",
                                                                "Terminated")));

    public static Optional<InstanceStatus> fromString(String value) {
        switch (value) {
            case "Initializing" : return Optional.of(Initializing); 
            case "Running" : return Optional.of(Running); 
            case "Restarting" : return Optional.of(Restarting); 
            case "Updating" : return Optional.of(Updating); 
            case "Maintaining" : return Optional.of(Maintaining); 
            case "PartiallyAvailable" : return Optional.of(PartiallyAvailable); 
            case "NotAvailable" : return Optional.of(NotAvailable); 
            case "Terminating" : return Optional.of(Terminating); 
            case "Terminated" : return Optional.of(Terminated); 
            default : return Optional.empty();
        }
    }

    public static boolean isValid(String value) {
        return AllPossiblesValues.contains(value);
    }

    public static boolean isNotInitializing(String value) {
        return !value.equals("Initializing");
    }

    public static boolean isNotRunning(String value) {
        return !value.equals("Running");
    }

    public static boolean isNotRestarting(String value) {
        return !value.equals("Restarting");
    }

    public static boolean isNotUpdating(String value) {
        return !value.equals("Updating");
    }

    public static boolean isNotMaintaining(String value) {
        return !value.equals("Maintaining");
    }

    public static boolean isNotPartiallyAvailable(String value) {
        return !value.equals("PartiallyAvailable");
    }

    public static boolean isNotNotAvailable(String value) {
        return !value.equals("NotAvailable");
    }

    public static boolean isNotTerminating(String value) {
        return !value.equals("Terminating");
    }

    public static boolean isNotTerminated(String value) {
        return !value.equals("Terminated");
    }

    public static void ifNotInitializing(String value, Consumer<InstanceStatus> fn) {
         if(isNotInitializing(value)) fn.accept(Initializing);
    }

    public static void ifNotRunning(String value, Consumer<InstanceStatus> fn) {
         if(isNotRunning(value)) fn.accept(Running);
    }

    public static void ifNotRestarting(String value, Consumer<InstanceStatus> fn) {
         if(isNotRestarting(value)) fn.accept(Restarting);
    }

    public static void ifNotUpdating(String value, Consumer<InstanceStatus> fn) {
         if(isNotUpdating(value)) fn.accept(Updating);
    }

    public static void ifNotMaintaining(String value, Consumer<InstanceStatus> fn) {
         if(isNotMaintaining(value)) fn.accept(Maintaining);
    }

    public static void ifNotPartiallyAvailable(String value, Consumer<InstanceStatus> fn) {
         if(isNotPartiallyAvailable(value)) fn.accept(PartiallyAvailable);
    }

    public static void ifNotNotAvailable(String value, Consumer<InstanceStatus> fn) {
         if(isNotNotAvailable(value)) fn.accept(NotAvailable);
    }

    public static void ifNotTerminating(String value, Consumer<InstanceStatus> fn) {
         if(isNotTerminating(value)) fn.accept(Terminating);
    }

    public static void ifNotTerminated(String value, Consumer<InstanceStatus> fn) {
         if(isNotTerminated(value)) fn.accept(Terminated);
    }

    public static boolean isInitializing(String value) {
        return value.equals("Initializing");
    }

    public static boolean isRunning(String value) {
        return value.equals("Running");
    }

    public static boolean isRestarting(String value) {
        return value.equals("Restarting");
    }

    public static boolean isUpdating(String value) {
        return value.equals("Updating");
    }

    public static boolean isMaintaining(String value) {
        return value.equals("Maintaining");
    }

    public static boolean isPartiallyAvailable(String value) {
        return value.equals("PartiallyAvailable");
    }

    public static boolean isNotAvailable(String value) {
        return value.equals("NotAvailable");
    }

    public static boolean isTerminating(String value) {
        return value.equals("Terminating");
    }

    public static boolean isTerminated(String value) {
        return value.equals("Terminated");
    }

    public static Optional<Initializing> asInitializingOption(String value) {
         return isInitializing(value) ? Optional.of(Initializing) : Optional.empty();
    }

    public static Optional<Running> asRunningOption(String value) {
         return isRunning(value) ? Optional.of(Running) : Optional.empty();
    }

    public static Optional<Restarting> asRestartingOption(String value) {
         return isRestarting(value) ? Optional.of(Restarting) : Optional.empty();
    }

    public static Optional<Updating> asUpdatingOption(String value) {
         return isUpdating(value) ? Optional.of(Updating) : Optional.empty();
    }

    public static Optional<Maintaining> asMaintainingOption(String value) {
         return isMaintaining(value) ? Optional.of(Maintaining) : Optional.empty();
    }

    public static Optional<PartiallyAvailable> asPartiallyAvailableOption(String value) {
         return isPartiallyAvailable(value) ? Optional.of(PartiallyAvailable) : Optional.empty();
    }

    public static Optional<NotAvailable> asNotAvailableOption(String value) {
         return isNotAvailable(value) ? Optional.of(NotAvailable) : Optional.empty();
    }

    public static Optional<Terminating> asTerminatingOption(String value) {
         return isTerminating(value) ? Optional.of(Terminating) : Optional.empty();
    }

    public static Optional<Terminated> asTerminatedOption(String value) {
         return isTerminated(value) ? Optional.of(Terminated) : Optional.empty();
    }

    public static void ifInitializing(String value, Consumer<Initializing> fn) {
         if(isInitializing(value)) fn.accept(Initializing);
    }

    public static void ifRunning(String value, Consumer<Running> fn) {
         if(isRunning(value)) fn.accept(Running);
    }

    public static void ifRestarting(String value, Consumer<Restarting> fn) {
         if(isRestarting(value)) fn.accept(Restarting);
    }

    public static void ifUpdating(String value, Consumer<Updating> fn) {
         if(isUpdating(value)) fn.accept(Updating);
    }

    public static void ifMaintaining(String value, Consumer<Maintaining> fn) {
         if(isMaintaining(value)) fn.accept(Maintaining);
    }

    public static void ifPartiallyAvailable(String value, Consumer<PartiallyAvailable> fn) {
         if(isPartiallyAvailable(value)) fn.accept(PartiallyAvailable);
    }

    public static void ifNotAvailable(String value, Consumer<NotAvailable> fn) {
         if(isNotAvailable(value)) fn.accept(NotAvailable);
    }

    public static void ifTerminating(String value, Consumer<Terminating> fn) {
         if(isTerminating(value)) fn.accept(Terminating);
    }

    public static void ifTerminated(String value, Consumer<Terminated> fn) {
         if(isTerminated(value)) fn.accept(Terminated);
    }

    public static Optional<InstanceStatus> asOneOf(String value, InstanceStatus ...selection) {
        for (InstanceStatus s : selection) {
            if(s.value.equals(value)) return Optional.of(s); 
        }
        return Optional.empty();
    }

    public static Consumer<Consumer<InstanceStatus>> ifOneOf(String value, InstanceStatus ...selection) {
        for (InstanceStatus s : selection) {
            if(s.value.equals(value)) return fn -> fn.accept(s);
        }
        return (ignored) -> {};
    }

    public static Consumer<Consumer<InstanceStatus>> ifNoneOf(String value, InstanceStatus ...selection) {
        for (InstanceStatus s : selection) {
            if(s.value.equals(value)) return (ignored) -> {};
        }
        return fn -> { fromString(value).ifPresent(fn); };
    }

    public <T> T fold(Function<Cases<T>, Expr.ExhaustiveMatching<InstanceStatus, T>> casesFn) {
        return casesFn.apply(new Cases<>()).apply(this);
    }

    public boolean isValid() {
        return isValid(this.value);
    }

    public boolean isNotInitializing() {
        return isNotInitializing(this.value);
    }

    public boolean isNotRunning() {
        return isNotRunning(this.value);
    }

    public boolean isNotRestarting() {
        return isNotRestarting(this.value);
    }

    public boolean isNotUpdating() {
        return isNotUpdating(this.value);
    }

    public boolean isNotMaintaining() {
        return isNotMaintaining(this.value);
    }

    public boolean isNotPartiallyAvailable() {
        return isNotPartiallyAvailable(this.value);
    }

    public boolean isNotNotAvailable() {
        return isNotNotAvailable(this.value);
    }

    public boolean isNotTerminating() {
        return isNotTerminating(this.value);
    }

    public boolean isNotTerminated() {
        return isNotTerminated(this.value);
    }

    public void ifNotInitializing(Consumer<InstanceStatus> fn) {
        ifNotInitializing(this.value, fn);
    }

    public void ifNotRunning(Consumer<InstanceStatus> fn) {
        ifNotRunning(this.value, fn);
    }

    public void ifNotRestarting(Consumer<InstanceStatus> fn) {
        ifNotRestarting(this.value, fn);
    }

    public void ifNotUpdating(Consumer<InstanceStatus> fn) {
        ifNotUpdating(this.value, fn);
    }

    public void ifNotMaintaining(Consumer<InstanceStatus> fn) {
        ifNotMaintaining(this.value, fn);
    }

    public void ifNotPartiallyAvailable(Consumer<InstanceStatus> fn) {
        ifNotPartiallyAvailable(this.value, fn);
    }

    public void ifNotNotAvailable(Consumer<InstanceStatus> fn) {
        ifNotNotAvailable(this.value, fn);
    }

    public void ifNotTerminating(Consumer<InstanceStatus> fn) {
        ifNotTerminating(this.value, fn);
    }

    public void ifNotTerminated(Consumer<InstanceStatus> fn) {
        ifNotTerminated(this.value, fn);
    }

    public boolean isInitializing() {
        return isInitializing(this.value);
    }

    public boolean isRunning() {
        return isRunning(this.value);
    }

    public boolean isRestarting() {
        return isRestarting(this.value);
    }

    public boolean isUpdating() {
        return isUpdating(this.value);
    }

    public boolean isMaintaining() {
        return isMaintaining(this.value);
    }

    public boolean isPartiallyAvailable() {
        return isPartiallyAvailable(this.value);
    }

    public boolean isNotAvailable() {
        return isNotAvailable(this.value);
    }

    public boolean isTerminating() {
        return isTerminating(this.value);
    }

    public boolean isTerminated() {
        return isTerminated(this.value);
    }

    public Optional<Initializing> asInitializingOption() {
         return asInitializingOption(this.value);
    }

    public Optional<Running> asRunningOption() {
         return asRunningOption(this.value);
    }

    public Optional<Restarting> asRestartingOption() {
         return asRestartingOption(this.value);
    }

    public Optional<Updating> asUpdatingOption() {
         return asUpdatingOption(this.value);
    }

    public Optional<Maintaining> asMaintainingOption() {
         return asMaintainingOption(this.value);
    }

    public Optional<PartiallyAvailable> asPartiallyAvailableOption() {
         return asPartiallyAvailableOption(this.value);
    }

    public Optional<NotAvailable> asNotAvailableOption() {
         return asNotAvailableOption(this.value);
    }

    public Optional<Terminating> asTerminatingOption() {
         return asTerminatingOption(this.value);
    }

    public Optional<Terminated> asTerminatedOption() {
         return asTerminatedOption(this.value);
    }

    public void ifInitializing(Consumer<Initializing> fn) {
        ifInitializing(this.value, fn);
    }

    public void ifRunning(Consumer<Running> fn) {
        ifRunning(this.value, fn);
    }

    public void ifRestarting(Consumer<Restarting> fn) {
        ifRestarting(this.value, fn);
    }

    public void ifUpdating(Consumer<Updating> fn) {
        ifUpdating(this.value, fn);
    }

    public void ifMaintaining(Consumer<Maintaining> fn) {
        ifMaintaining(this.value, fn);
    }

    public void ifPartiallyAvailable(Consumer<PartiallyAvailable> fn) {
        ifPartiallyAvailable(this.value, fn);
    }

    public void ifNotAvailable(Consumer<NotAvailable> fn) {
        ifNotAvailable(this.value, fn);
    }

    public void ifTerminating(Consumer<Terminating> fn) {
        ifTerminating(this.value, fn);
    }

    public void ifTerminated(Consumer<Terminated> fn) {
        ifTerminated(this.value, fn);
    }

    public Optional<InstanceStatus> asOneOf(InstanceStatus ...selection) {
        for (InstanceStatus s : selection) {
            if(s.value.equals(value)) return Optional.of(s); 
        }
        return Optional.empty();
    }

    public Consumer<Consumer<InstanceStatus>> ifOneOf(InstanceStatus ...selection) {
        for (InstanceStatus s : selection) {
            if(s.value.equals(value)) return fn -> fn.accept(this); 
        }
        return (ignored) -> {};
    }

    public Consumer<Consumer<InstanceStatus>> ifNoneOf(InstanceStatus ...selection) {
        for (InstanceStatus s : selection) {
            if(s.value.equals(value)) return (ignored) -> {};
        }
        return fn -> fn.accept(this);
    }

    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstanceStatus)) return false;
        InstanceStatus other = (InstanceStatus) o;
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