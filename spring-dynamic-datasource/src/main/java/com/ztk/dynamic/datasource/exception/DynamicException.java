/*
 * description
 */
package com.ztk.dynamic.datasource.exception;

import com.ztk.dynamic.datasource.utils.format.MessageFormatter;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DynamicException extends RuntimeException {

    public DynamicException() {
    }

    public DynamicException(String message, Object... params) {
        super(MessageFormatter.arrayFormat(message, params).getMessage());
    }

    public DynamicException(String message, Throwable cause, Object... params) {
        super(MessageFormatter.arrayFormat(message, params).getMessage(), cause);
    }

    public DynamicException(Throwable cause) {
        super(cause);
    }

}
