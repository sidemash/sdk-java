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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import static java.util.function.Function.identity;

public final class UpdateStreamSquareForm {
    private final String id;
    private final Map<EditableFields, Object> set;
    private final Set<RemovableFields> remove;

    public UpdateStreamSquareForm(Builder builder) {
        Objects.requireNonNull(builder, "In UpdateStreamSquareForm, the builder can not be null");
        this.id     = builder.id;
        this.set    = builder.set;
        this.remove = builder.remove;
    }

    public static Builder byId(String id) {
        return new Builder(Objects.requireNonNull(id, "In UpdateStreamSquareForm.Builder, the param 'id' of type String can not be null")); 
    }

    public static final class Builder {
        private String id;
        private Set<RemovableFields> remove;
        private Map<EditableFields, Object> set;

        @JsonCreator
        protected Builder(@JsonProperty("id") String id) {
            this(id, new HashSet<>(), new HashMap<>());
        }
        @JsonCreator
        protected Builder(@JsonProperty("id") String id,
                          @JsonProperty("remove") Set<RemovableFields> remove,
                          @JsonProperty("set") Map<EditableFields,
                          Object> set) {
            this.id = id;
            this.remove = remove;
            this.set = set;
        }

        public Builder removeDescription() {
            this.remove.add(RemovableFields.DESCRIPTION);
            return this;
        }

        public Builder removeForeignData() {
            this.remove.add(RemovableFields.FOREIGN_DATA);
            return this;
        }

        public Builder setIsElastic(boolean i) {
            this.set.put(EditableFields.IS_ELASTIC, i);
            return this;
        }

        public Builder setIsElasticOption(Optional<Boolean> i) {
            Optional.ofNullable(i).flatMap(identity()).ifPresent(nonNullValue -> 
                this.set.put(EditableFields.IS_ELASTIC, nonNullValue)
            );
            return this;
        }

        public Builder setSize(StreamSquare.Size s) {
            this.set.put(EditableFields.SIZE, Objects.requireNonNull(s, "In UpdateStreamSquareForm.Builder, the param 's' of type StreamSquare.Size can not be null"));
            return this;
        }

        public Builder setSizeOption(Optional<StreamSquare.Size> s) {
            Optional.ofNullable(s).flatMap(identity()).ifPresent(nonNullValue -> 
                this.set.put(EditableFields.SIZE, nonNullValue)
            );
            return this;
        }

        public Builder setHook(Hook h) {
            this.set.put(EditableFields.HOOK, Objects.requireNonNull(h, "In UpdateStreamSquareForm.Builder, the param 'h' of type Hook can not be null"));
            return this;
        }

        public Builder setHookOption(Optional<Hook> h) {
            Optional.ofNullable(h).flatMap(identity()).ifPresent(nonNullValue -> 
                this.set.put(EditableFields.HOOK, nonNullValue)
            );
            return this;
        }

        public Builder setDescription(String d) {
            this.set.put(EditableFields.DESCRIPTION, Objects.requireNonNull(d, "In UpdateStreamSquareForm.Builder, the param 'd' of type String can not be null"));
            return this;
        }

        public Builder setDescriptionOption(Optional<String> d) {
            Optional.ofNullable(d).flatMap(identity()).ifPresent(nonNullValue -> 
                this.set.put(EditableFields.DESCRIPTION, nonNullValue)
            );
            return this;
        }

        public Builder setForeignData(String f) {
            this.set.put(EditableFields.FOREIGN_DATA, Objects.requireNonNull(f, "In UpdateStreamSquareForm.Builder, the param 'f' of type String can not be null"));
            return this;
        }

        public Builder setForeignDataOption(Optional<String> f) {
            Optional.ofNullable(f).flatMap(identity()).ifPresent(nonNullValue -> 
                this.set.put(EditableFields.FOREIGN_DATA, nonNullValue)
            );
            return this;
        }

        public UpdateStreamSquareForm build() {
            return new UpdateStreamSquareForm(this);
        }
    }

    public enum RemovableFields {
        DESCRIPTION {
            @Override
            public String toString() {
                return "description"; 
            }
        },
        FOREIGN_DATA {
            @Override
            public String toString() {
                return "foreignData"; 
            }
        }
    }

    public enum EditableFields {
        IS_ELASTIC {
            @Override
            public String toString() {
                return "isElastic"; 
            }
        },
        SIZE {
            @Override
            public String toString() {
                return "size"; 
            }
        },
        HOOK {
            @Override
            public String toString() {
                return "hook"; 
            }
        },
        DESCRIPTION {
            @Override
            public String toString() {
                return "description"; 
            }
        },
        FOREIGN_DATA {
            @Override
            public String toString() {
                return "foreignData"; 
            }
        }
    }

    public String getId() { return id; }

    public String toJson() {
        return Utils.Js.toJson(this);
    }

    public JsonNode toJsonNode() {
        return Utils.Js.toJsonNode(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateStreamSquareForm)) return false;
        UpdateStreamSquareForm other = (UpdateStreamSquareForm) o;
        return Objects.equals(id, other.id) &&
               Objects.equals(remove, other.remove) &&
               Objects.equals(set, other.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, remove, set);
    }

    @Override
    public String toString() {
        return "UpdateStreamSquareForm{id=" + id +
                                      ", remove=" + remove +
                                      ", set=" + set + '}';
    }
}