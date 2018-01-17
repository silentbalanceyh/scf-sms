package com.kingxunlian.exception.internal;

/**
 * 常量包
 */
interface Info {
    /**
     * 配置
     */
    String KEY_MISSING = "The key {1} of property file {0} missing, please check.";
    /**
     *
     */
    String KEY_CONFLICT = "The key {1} of property file {0} data type is wrong. Expected = {2}, Actual = {3}.";
    /**
     * 500 Internal Error
     */
    String EMPTY_STREAM = "Empty stream from file {0}, it may does not exist.";

    /**
     * 定制异常
     */
    String NIL_INPUT = "[DM-HORS] ({0}) Input argument is null, it''s invalid.";
    /**
     * 参数长度检查
     */
    String MINLEN_INPUT = "[DM-HORS] ({0}) Expected min minLen (expr: {2} > {1}), but current minLen = {2}.";
    /**
     * 参数长度检查
     */
    String LEN_INPUT = "[DM-HORS] ({0}) Expected actual length {1}, but current length = {2}.";
    /**
     * Null Reference检查点，理论上不会报
     */
    String NULL_REF = "[DM-HORS] ({0}) Found null reference when call the chain of methods: Position ( index + 1 ) = {1}. Examples: \n" +
            "HocFluent.execNull( \n" +
            "\t1: (start) -> method1(): chain1, \n" +
            "\t2: (chain1) -> method2(): chain2, \n" +
            "\t3: (chain2) -> method3(): chain3, \n" +
            "\t4: (chain3) -> method4(): chain4, \n" +
            "\t5: ......" +
            ") The parameter chain{1} is null";
}
