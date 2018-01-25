
package com.fasterxml.jackson.core;

import java.util.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class JsonArray implements Iterable<Object> {

    private List<Object> list;

    public JsonArray(final String json) {
        this.fromJson(json);
    }

    public JsonArray() {
        this.list = new ArrayList<>();
    }

    public JsonArray(final List list) {
        this.list = list;
    }

    static boolean arrayEquals(final List<?> l1, final Object o2) {
        final List<?> l2;
        if (o2 instanceof JsonArray) {
            l2 = ((JsonArray) o2).list;
        } else if (o2 instanceof List<?>) {
            l2 = (List<?>) o2;
        } else {
            return false;
        }
        if (l1.size() != l2.size()) {
            return false;
        }
        final Iterator<?> iter = l2.iterator();
        for (final Object entry : l1) {
            final Object other = iter.next();
            if (entry == null) {
                if (other != null) {
                    return false;
                }
            } else if (!JsonObject.equals(entry, other)) {
                return false;
            }
        }
        return true;
    }

    public String getString(final int pos) {
        final CharSequence cs = (CharSequence) this.list.get(pos);
        return cs == null ? null : cs.toString();
    }

    public Integer getInteger(final int pos) {
        final Number number = (Number) this.list.get(pos);
        return Json.toInteger(number);
    }

    public Long getLong(final int pos) {
        final Number number = (Number) this.list.get(pos);
        return Json.toLong(number);
    }

    public Double getDouble(final int pos) {
        final Number number = (Number) this.list.get(pos);
        return Json.toDouble(number);
    }

    public Float getFloat(final int pos) {
        final Number number = (Number) this.list.get(pos);
        return Json.toFloat(number);
    }

    public Boolean getBoolean(final int pos) {
        return (Boolean) this.list.get(pos);
    }

    public JsonObject getJsonObject(final int pos) {
        Object val = this.list.get(pos);
        if (val instanceof Map) {
            val = new JsonObject((Map) val);
        }
        return (JsonObject) val;
    }

    public JsonArray getJsonArray(final int pos) {
        Object val = this.list.get(pos);
        if (val instanceof List) {
            val = new JsonArray((List) val);
        }
        return (JsonArray) val;
    }

    public Object getValue(final int pos) {
        Object val = this.list.get(pos);
        if (val instanceof Map) {
            val = new JsonObject((Map) val);
        } else if (val instanceof List) {
            val = new JsonArray((List) val);
        }
        return val;
    }

    public boolean hasNull(final int pos) {
        return this.list.get(pos) == null;
    }

    public JsonArray add(final Enum value) {
        Objects.requireNonNull(value);
        this.list.add(value.name());
        return this;
    }

    public JsonArray add(final CharSequence value) {
        Objects.requireNonNull(value);
        this.list.add(value.toString());
        return this;
    }

    public JsonArray add(final String value) {
        Objects.requireNonNull(value);
        this.list.add(value);
        return this;
    }

    public JsonArray add(final Integer value) {
        Objects.requireNonNull(value);
        this.list.add(value);
        return this;
    }

    public JsonArray add(final Long value) {
        Objects.requireNonNull(value);
        this.list.add(value);
        return this;
    }

    public JsonArray add(final Double value) {
        Objects.requireNonNull(value);
        this.list.add(value);
        return this;
    }

    public JsonArray add(final Float value) {
        Objects.requireNonNull(value);
        this.list.add(value);
        return this;
    }

    public JsonArray add(final Boolean value) {
        Objects.requireNonNull(value);
        this.list.add(value);
        return this;
    }

    public JsonArray addNull() {
        this.list.add(null);
        return this;
    }

    public JsonArray add(final JsonObject value) {
        Objects.requireNonNull(value);
        this.list.add(value);
        return this;
    }

    public JsonArray add(final JsonArray value) {
        Objects.requireNonNull(value);
        this.list.add(value);
        return this;
    }

    public JsonArray add(Object value) {
        Objects.requireNonNull(value);
        value = Json.checkAndCopy(value, false);
        this.list.add(value);
        return this;
    }

    public JsonArray addAll(final JsonArray array) {
        Objects.requireNonNull(array);
        this.list.addAll(array.list);
        return this;
    }

    public boolean contains(final Object value) {
        return this.list.contains(value);
    }

    public boolean remove(final Object value) {
        return this.list.remove(value);
    }

    public Object remove(final int pos) {
        final Object removed = this.list.remove(pos);
        if (removed instanceof Map) {
            return new JsonObject((Map) removed);
        } else if (removed instanceof ArrayList) {
            return new JsonArray((List) removed);
        }
        return removed;
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public List getList() {
        return this.list;
    }

    public JsonArray clear() {
        this.list.clear();
        return this;
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iter(this.list.iterator());
    }

    public String encode() {
        return Json.encode(this.list);
    }

    public String encodePrettily() {
        return Json.encodePrettily(this.list);
    }

    public JsonArray copy() {
        final List<Object> copiedList = new ArrayList<>(this.list.size());
        for (Object val : this.list) {
            val = Json.checkAndCopy(val, true);
            copiedList.add(val);
        }
        return new JsonArray(copiedList);
    }

    @Override
    public String toString() {
        return this.encode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return arrayEquals(this.list, o);
    }

    @Override
    public int hashCode() {
        return this.list.hashCode();
    }

    private void fromJson(final String json) {
        this.list = Json.decodeValue(json, List.class);
    }

    private class Iter implements Iterator<Object> {

        final Iterator<Object> listIter;

        Iter(final Iterator<Object> listIter) {
            this.listIter = listIter;
        }

        @Override
        public boolean hasNext() {
            return this.listIter.hasNext();
        }

        @Override
        public Object next() {
            Object val = this.listIter.next();
            if (val instanceof Map) {
                val = new JsonObject((Map) val);
            } else if (val instanceof List) {
                val = new JsonArray((List) val);
            }
            return val;
        }

        @Override
        public void remove() {
            this.listIter.remove();
        }
    }
}
