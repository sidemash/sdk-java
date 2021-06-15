package com.sidemash.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

import static com.sidemash.sdk.Expr.*;
import static com.sidemash.sdk.ListFormOld.*;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ListFormOld listForm = ListForm(limit(500).orderBy("createdTime:DESC").where("a.id=14"));
        StreamSquare s2 = null; //  InstanceStatus.NotAvailable;
        if (s2.getSize() == StreamSquare.Size.M){

        }
        else {

        }
        /*
        s2.getStatus().fold(cases ->
                cases.whenInitializing(s -> s.toString().charAt(0))
                        .whenRunning(s->s.toString().charAt(0))
                        .whenRestarting(s->s.toString().charAt(0))
                        .whenUpdating(InstanceStatus::toString)
        );
        s2.getStatus().fold(cases ->
                cases.matching().whenRunning(and(() -> rt > 45, ))

                        //.when(oneOf(InstanceStatus.Initializing, InstanceStatus.Terminated), InstanceStatus::toString)
                        //.otherwise(InstanceStatus::toString)
        );*/








        /*
        System.out.println(listForm.toString());
        System.out.println(listForm.toQueryString());
        System.out.println(Utils.Js.mapper.writeValueAsString(listForm));
        System.out.println(Utils.Js.mapper.readValue("{\"limit\":500,\"orderBy\":\"createdTime:DESC\",\"time\":\"2020-10-22T22:33:32.196Z\"}", ListForm.class));

        */
        // System.out.println(InstanceStatus.Initializing);
        // System.out.println(Utils.Js.mapper.writeValueAsString(listForm));
        //System.out.println(Utils.Js.mapper.readValue("\"Initializing\"", InstanceStatus.class));
        //System.out.println(Utils.Js.mapper.writeValueAsString(InstanceStatus.Initializing));
        Hook.HttpCall h = new Hook.HttpCall(Hook.HttpCall.Method.GET, "https://lol.com");
        //System.out.println(Utils.Js.mapper.writeValueAsString(h));
        System.out.println(Utils.Js.Mapper.readValue("{\"_type\":\"Hook.HttpCall\",\"method\":\"GET\",\"url\":\"https://lol.com\"}", Hook.class));
        //System.out.println(h.getType());

        System.out.println(
                InstanceStatus.NotAvailable
                        .<String>fold(cases ->
                                cases.whenInitializing(i -> "In")
                                        .whenRunning(r -> "Ru")
                                        .whenRestarting(r -> "Re")
                                        .whenUpdating(r -> "Up")
                                        .whenMaintaining(r -> "Ma")
                                        .whenPartiallyAvailable(r -> "Pa")
                                        .whenNotAvailable(r -> "No")
                                        .whenTerminating(r -> "Tering")
                                        .whenTerminated(r -> "Tered")
                        )

        );

        /*


        class
            @NonNull String id;
            @NonNull Set<String> remove;
            @NonNull Map<String, S> set;
            inner class Selecting
            UpdateS2Form

        OK static by
        OK constructor with builder
        OK constructor with fields
        OK all imports excepts self

        enum RemovableFields { toString }
        enum EditableField  { toString }
        Builder
            UpdateS2Form.byId()
                        .removeDescription()
                        .removeForeignData()
                        .setDescription(newValue)
                        .setForeignData(newValue)
                        .build() will copy the set and the map

        Equals
        HashCode
        toString

        Serializer
        ToJson()
        ToJsonNode()

        DeSerializer
        FromJson(String s)
        FromJsonNode(JsonNode node)

        RSimpleSum
        RComplexSum


        object JsonSe
        object JsonDe

        MethodDesc(name, arg, body)
        CreateEnum(method: Seq[MethodDesc])  extends Create
        CreateSimpleSum(rSimpleSum)  extends Create

        CreateBuilder(spec:Some(BuilderSpec()))  extends Create

        CreateProduct(rProduct,
                      inner:Seq[Create]
                      builderSpec:Some(BuilderSpec()),
                      withJsonSerializer,
                      withGetterAndSetters,
                      withJsonDeserializer,
                      withEqualsAndHashCode,
                      withToString) extends Create

        CreateComplexSum(rComplexSum,
                         builder:Some(BuilderSpec()),
                         withJsonSerializer,
                         withGetterAndSetters,
                         withJsonDeserializer,
                         withEqualsAndHashCode,
                         withToString)  extends Create
        */
    }
}


/*
// Generated
    public static class CasesDef<A, T> {
        public CaseS<A, T> whenS(Function<A, T> fn) {
            List<Expr.Case<A, T>> previousCases = new ArrayList<>();
            return new CaseS<>(previousCases, fn);
        }

        public MatchingGenerated<A, T> matching() { return new MatchingGenerated<>(new ArrayList<>()); }
    }

    public static class MatchingGenerated<A, T> extends Expr.Matching<A, T> {
        public MatchingGenerated(List<Expr.Case<A, T>> cases) {
            super(cases);
        }
        public MatchingGenerated<A, T> when(BooleanSupplier condition, Function<A, T> fn) { return new MatchingGenerated<>(allWhenConditionCases(condition, fn, null)); }
        public MatchingGenerated<A, T> whenL(Expr.Guard and, Function<A, T> fn) { return new MatchingGenerated<>(allWhenConditionCases(and, fn, "Initializing")); }
        public MatchingGenerated<A, T> whenL(Function<A, T> fn) { return new MatchingGenerated<>(allWhenConditionCases(() -> true, fn, "Initializing")); }
        public MatchingGenerated<A, T> whenS(Expr.Guard and, Function<A, T> fn) { return new MatchingGenerated<>(allWhenConditionCases(and, fn, "Restarting")); }
        public MatchingGenerated<A, T> whenS(Function<A, T> fn) { return new MatchingGenerated<>(allWhenConditionCases(() -> true, fn, "Restarting")); }
    }


    public static class CaseS<A, T> extends Expr.CaseValue<A, T> {
        public CaseS(List<Expr.Case<A, T>> previousCases, Function<A, T> fn) {
            super(previousCases, fn, "Restarting");
        }

        public Expr.ExhaustiveMatching<A, T> whenL(Function<A, T> fn) {
            List<Expr.Case<A, T>> previousCases = new ArrayList<>(this.previousCases);
            previousCases.add(this);
            CaseL<A, T> caseL = new CaseL<>(previousCases, fn);
            return caseL.whenValue(fn);
        }
    }

    public static class CaseL<A, T> extends Expr.CaseValue<A, T> {
        public CaseL(List<Expr.Case<A, T>> previousCases, Function<A, T> fn) {
            super(previousCases, fn, "Initializing");
        }
    }
 */