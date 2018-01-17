package com.kingxunlian.platform.tool;

import com.fasterxml.jackson.core.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.*;
import java.util.*;

/**
 * IO读取专用类
 */
public final class IO {

    private static final ObjectMapper YAML = new YAMLMapper();

    private IO() {
    }

    public static JsonObject getJObject(final String filename) {
        return new JsonObject(IO.getContent(filename));
    }

    /**
     * @param in
     * @return
     */
    public static String getContent(final InputStream in) {
        final StringBuilder buffer = new StringBuilder(16);
        final String[] lines = getLines(in);
        for (final String line : lines) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    public static String[] getLines(final InputStream in) {
        final List<String> lineList = new ArrayList<>();
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while (null != (line = reader.readLine())) {
                lineList.add(line);
            }
            reader.close();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return lineList.toArray(new String[]{});
    }

    /**
     * 读取字符串
     *
     * @param filename
     * @return
     */
    public static String getContent(final String filename) {
        final InputStream in = read(filename, null);
        return getContent(in);
    }

    /**
     * 读取所有行
     *
     * @param filename
     * @return
     */
    public static String[] getLines(final String filename) {
        final InputStream in = read(filename, null);
        return getLines(in);
    }

    /**
     * 读取属性文件
     *
     * @param filename
     * @return
     */
    public static Properties getProp(final String filename) {
        final Properties prop = new Properties();
        try {
            final InputStream in = read(filename, null);
            prop.load(in);
            in.close();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    /**
     * @param filename
     * @return
     */
    public static InputStream getStream(final String filename) {
        return read(filename, IO.class);
    }

    /**
     * 读取配置文件
     *
     * @param filename
     * @return
     */
    public static Map<String, String> getMap(final String filename) {
        final Properties prop = getProp(filename);
        final Map<String, String> map = new HashMap<>();
        for (final Object key : prop.keySet()) {
            if (null != key) {
                final String targetKey = key.toString();
                final String value = prop.getProperty(targetKey);
                map.put(targetKey, value);
            }
        }
        return map;
    }

    /**
     * 根据路径读取
     *
     * @param filename
     * @return
     */
    private static InputStream read(final String filename, final Class<?> clazz) {
        // 1.检查失败包含了clazz
        final InputStream in;
        final File file = new File(filename);
        if (file.exists()) {
            in = stream(file);
        } else {
            if (null == clazz) {
                // 2.先检查当前线程资源
                in = stream(filename);
            } else {
                // 3.直接从当前类路径下检索
                in = stream(filename, clazz);
            }
        }
        // 4.再检查spring的docker路径
        return in;
    }

    /**
     * 从文件流中读取资源文件
     *
     * @param file
     * @return
     */
    private static InputStream stream(final File file) {
        try {
            InputStream in = null;
            if (file.exists() && file.isFile()) {
                in = new FileInputStream(file);
            }
            return in;
        } catch (final Exception ex) {
            return null;
        }
    }

    /**
     * 从传入类所在类加载器中加载资源文件
     *
     * @param filename
     * @param clazz
     * @return
     */
    private static InputStream stream(final String filename, final Class<?> clazz) {
        return clazz.getResourceAsStream(filename);
    }

    /**
     * 从当前类加载器中读取资源
     *
     * @param filename
     * @return
     */
    private static InputStream stream(final String filename) {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return loader.getResourceAsStream(filename);
    }
}
