
package com.fasterxml.jackson.core;

import java.util.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class JsonObject implements Iterable<Map.Entry<String, Object>> {

    private Map<String, Object> map;

    public JsonObject(final String json) {
        fromJson(json);
    }

    public JsonObject() {
        map = new LinkedHashMap<>();
    }

    public JsonObject(final Map<String, Object> map) {
        this.map = map;
    }

    public static JsonObject mapFrom(final Object obj) {
        return new JsonObject((Map<String, Object>) Json.mapper.convertValue(obj, Map.class));
    }

    public <T> T mapTo(final Class<T> type) {
        return Json.mapper.convertValue(map, type);
    }

    public String getString(final String key) {
        Objects.requireNonNull(key);
        final CharSequence cs = (CharSequence) map.get(key);
        return cs == null ? null : cs.toString();
    }

    public Integer getInteger(final String key) {
        Objects.requireNonNull(key);
        final Number number = (Number) map.get(key);
        return Json.toInteger(number);
    }

    public Long getLong(final String key) {
        Objects.requireNonNull(key);
        final Number number = (Number) map.get(key);
        return Json.toLong(number);
    }

    public Double getDouble(final String key) {
        Objects.requireNonNull(key);
        final Number number = (Number) map.get(key);
        return Json.toDouble(number);
    }

    public Float getFloat(final String key) {
        Objects.requireNonNull(key);
        final Number number = (Number) map.get(key);
        return Json.toFloat(number);
    }

    public Boolean getBoolean(final String key) {
        Objects.requireNonNull(key);
        return (Boolean) map.get(key);
    }

    public JsonObject getJsonObject(final String key) {
        Objects.requireNonNull(key);
        Object val = map.get(key);
        if (val instanceof Map) {
            val = new JsonObject((Map) val);
        }
        return (JsonObject) val;
    }

    public JsonArray getJsonArray(final String key) {
        Objects.requireNonNull(key);
        Object val = map.get(key);
        if (val instanceof List) {
            val = new JsonArray((List) val);
        }
        return (JsonArray) val;
    }

    public Object getValue(final String key) {
        Objects.requireNonNull(key);
        Object val = map.get(key);
        if (val instanceof Map) {
            val = new JsonObject((Map) val);
        } else if (val instanceof List) {
            val = new JsonArray((List) val);
        }
        return val;
    }

    public String getString(final String key, final String def) {
        Objects.requireNonNull(key);
        final CharSequence cs = (CharSequence) map.get(key);
        return cs != null || map.containsKey(key) ? cs == null ? null : cs.toString() : def;
    }

    public Integer getInteger(final String key, final Integer def) {
        Objects.requireNonNull(key);
        final Number val = (Number) map.get(key);
        if (val == null) {
            if (map.containsKey(key)) {
                return null;
            } else {
                return def;
            }
        } else if (val instanceof Integer) {
            return (Integer) val; // Avoids unnecessary unbox/box
        } else {
            return val.intValue();
        }
    }

    public Long getLong(final String key, final Long def) {
        Objects.requireNonNull(key);
        final Number val = (Number) map.get(key);
        if (val == null) {
            if (map.containsKey(key)) {
                return null;
            } else {
                return def;
            }
        } else if (val instanceof Long) {
            return (Long) val; // Avoids unnecessary unbox/box
        } else {
            return val.longValue();
        }
    }

    public Double getDouble(final String key, final Double def) {
        Objects.requireNonNull(key);
        final Number val = (Number) map.get(key);
        if (val == null) {
            if (map.containsKey(key)) {
                return null;
            } else {
                return def;
            }
        } else if (val instanceof Double) {
            return (Double) val; // Avoids unnecessary unbox/box
        } else {
            return val.doubleValue();
        }
    }

    public Float getFloat(final String key, final Float def) {
        Objects.requireNonNull(key);
        final Number val = (Number) map.get(key);
        if (val == null) {
            if (map.containsKey(key)) {
                return null;
            } else {
                return def;
            }
        } else if (val instanceof Float) {
            return (Float) val; // Avoids unnecessary unbox/box
        } else {
            return val.floatValue();
        }
    }

    public Boolean getBoolean(final String key, final Boolean def) {
        Objects.requireNonNull(key);
        final Object val = map.get(key);
        return val != null || map.containsKey(key) ? (Boolean) val : def;
    }

    public JsonObject getJsonObject(final String key, final JsonObject def) {
        final JsonObject val = getJsonObject(key);
        return val != null || map.containsKey(key) ? val : def;
    }

    public JsonArray getJsonArray(final String key, final JsonArray def) {
        final JsonArray val = getJsonArray(key);
        return val != null || map.containsKey(key) ? val : def;
    }

    public Object getValue(final String key, final Object def) {
        Objects.requireNonNull(key);
        final Object val = getValue(key);
        return val != null || map.containsKey(key) ? val : def;
    }

    public boolean containsKey(final String key) {
        Objects.requireNonNull(key);
        return map.containsKey(key);
    }

    public Set<String> fieldNames() {
        return map.keySet();
    }

    public JsonObject put(final String key, final Enum value) {
        Objects.requireNonNull(key);
        map.put(key, value == null ? null : value.name());
        return this;
    }

    public JsonObject put(final String key, final CharSequence value) {
        Objects.requireNonNull(key);
        map.put(key, value == null ? null : value.toString());
        return this;
    }

    public JsonObject put(final String key, final String value) {
        Objects.requireNonNull(key);
        map.put(key, value);
        return this;
    }

    public JsonObject put(final String key, final Integer value) {
        Objects.requireNonNull(key);
        map.put(key, value);
        return this;
    }

    public JsonObject put(final String key, final Long value) {
        Objects.requireNonNull(key);
        map.put(key, value);
        return this;
    }

    public JsonObject put(final String key, final Double value) {
        Objects.requireNonNull(key);
        map.put(key, value);
        return this;
    }

    public JsonObject put(final String key, final Float value) {
        Objects.requireNonNull(key);
        map.put(key, value);
        return this;
    }

    public JsonObject put(final String key, final Boolean value) {
        Objects.requireNonNull(key);
        map.put(key, value);
        return this;
    }

    public JsonObject putNull(final String key) {
        Objects.requireNonNull(key);
        map.put(key, null);
        return this;
    }

    public JsonObject put(final String key, final JsonObject value) {
        Objects.requireNonNull(key);
        map.put(key, value);
        return this;
    }

    public JsonObject put(final String key, final JsonArray value) {
        Objects.requireNonNull(key);
        map.put(key, value);
        return this;
    }

    public JsonObject put(final String key, Object value) {
        Objects.requireNonNull(key);
        value = Json.checkAndCopy(value, false);
        map.put(key, value);
        return this;
    }

    public Object remove(final String key) {
        return map.remove(key);
    }

    public JsonObject mergeIn(final JsonObject other) {
        return mergeIn(other, false);
    }

    public JsonObject mergeIn(final JsonObject other, final boolean deep) {
        return mergeIn(other, deep ? Integer.MAX_VALUE : 1);
    }

    public JsonObject mergeIn(final JsonObject other, final int depth) {
        if (depth < 1) {
            return this;
        }
        if (depth == 1) {
            map.putAll(other.map);
            return this;
        }
        for (final Map.Entry<String, Object> e : other.map.entrySet()) {
            /*map.merge(e.getKey(), e.getValue(), (oldVal, newVal) -> {
                if (oldVal instanceof Map) {
                    oldVal = new JsonObject((Map) oldVal);
                }
                if (newVal instanceof Map) {
                    newVal = new JsonObject((Map) newVal);
                }
                if (oldVal instanceof JsonObject && newVal instanceof JsonObject) {
                    return ((JsonObject) oldVal).mergeIn((JsonObject) newVal, depth - 1);
                }
                return newVal;
            });*/
        }
        return this;
    }

    public String encode() {
        return Json.encode(map);
    }

    public String encodePrettily() {
        return Json.encodePrettily(map);
    }

    public JsonObject copy() {
        final Map<String, Object> copiedMap;
        if (map instanceof LinkedHashMap) {
            copiedMap = new LinkedHashMap<>(map.size());
        } else {
            copiedMap = new HashMap<>(map.size());
        }
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            Object val = entry.getValue();
            val = Json.checkAndCopy(val, true);
            copiedMap.put(entry.getKey(), val);
        }
        return new JsonObject(copiedMap);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public Iterator<Map.Entry<String, Object>> iterator() {
        return new Iter(map.entrySet().iterator());
    }

    public int size() {
        return map.size();
    }

    public JsonObject clear() {
        map.clear();
        return this;
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public String toString() {
        return encode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return objectEquals(map, o);
    }

    static boolean objectEquals(final Map<?, ?> m1, final Object o2) {
        final Map<?, ?> m2;
        if (o2 instanceof JsonObject) {
            m2 = ((JsonObject) o2).map;
        } else if (o2 instanceof Map<?, ?>) {
            m2 = (Map<?, ?>) o2;
        } else {
            return false;
        }
        if (m1.size() != m2.size())
            return false;
        for (final Map.Entry<?, ?> entry : m1.entrySet()) {
            final Object val = entry.getValue();
            if (val == null) {
                if (m2.get(entry.getKey()) != null) {
                    return false;
                }
            } else {
                if (!equals(entry.getValue(), m2.get(entry.getKey()))) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean equals(final Object o1, final Object o2) {
        if (o1 == o2)
            return true;
        if (o1 instanceof JsonObject) {
            return objectEquals(((JsonObject) o1).map, o2);
        }
        if (o1 instanceof Map<?, ?>) {
            return objectEquals((Map<?, ?>) o1, o2);
        }
        if (o1 instanceof JsonArray) {
            return JsonArray.arrayEquals(((JsonArray) o1).getList(), o2);
        }
        if (o1 instanceof List<?>) {
            return JsonArray.arrayEquals((List<?>) o1, o2);
        }
        if (o1 instanceof Number && o2 instanceof Number && o1.getClass() != o2.getClass()) {
            final Number n1 = (Number) o1;
            final Number n2 = (Number) o2;
            if (o1 instanceof Float || o1 instanceof Double || o2 instanceof Float || o2 instanceof Double) {
                return n1.doubleValue() == n2.doubleValue();
            } else {
                return n1.longValue() == n2.longValue();
            }
        }
        return o1.equals(o2);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    private void fromJson(final String json) {
        map = Json.decodeValue(json, Map.class);
    }

    private class Iter implements Iterator<Map.Entry<String, Object>> {

        final Iterator<Map.Entry<String, Object>> mapIter;

        Iter(final Iterator<Map.Entry<String, Object>> mapIter) {
            this.mapIter = mapIter;
        }

        @Override
        public boolean hasNext() {
            return mapIter.hasNext();
        }

        @Override
        public Map.Entry<String, Object> next() {
            final Map.Entry<String, Object> entry = mapIter.next();
            if (entry.getValue() instanceof Map) {
                return new Entry(entry.getKey(), new JsonObject((Map) entry.getValue()));
            } else if (entry.getValue() instanceof List) {
                return new Entry(entry.getKey(), new JsonArray((List) entry.getValue()));
            }
            return entry;
        }

        @Override
        public void remove() {
            mapIter.remove();
        }
    }

    private static final class Entry implements Map.Entry<String, Object> {
        final String key;
        final Object value;

        public Entry(final String key, final Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(final Object value) {
            throw new UnsupportedOperationException();
        }
    }
}
