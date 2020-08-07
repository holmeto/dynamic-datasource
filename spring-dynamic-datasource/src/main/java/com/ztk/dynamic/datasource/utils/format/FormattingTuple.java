package com.ztk.dynamic.datasource.utils.format;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class FormattingTuple {
    public static FormattingTuple NULL = new FormattingTuple((String) null);
    private String message;
    private Object[] argArray;

    public FormattingTuple(String message) {
        this(message, (Object[]) null);
    }

    public FormattingTuple(String message, Object[] argArray) {
        this.message = message;
        this.argArray = argArray;
    }

    public String getMessage() {
        return this.message;
    }

    public Object[] getArgArray() {
        return this.argArray;
    }

}