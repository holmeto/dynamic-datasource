package com.ztk.dynamic.datasource.scanner.auto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public interface BaseConfiguration {

    String BASE_PACKAGES = "basePackages";
    String BASE_PACKAGE_CLASSES = "basePackageClasses";
    String TYPE = "type";
    String ENABLE_SHARDING = "sharding";

    Map<String, String[]> attributes = new ConcurrentHashMap<>();

    /**
     * set attribute array
     *
     * @param classes
     * @return
     */
    default BaseConfiguration setAttributes(Class... classes) {
        int length = classes.length;
        String[] names = new String[length];
        for (int i = 0; i < length; i++) {
            Class clazz = classes[i];
            String name = clazz.getName();
            name = name.substring(0, name.lastIndexOf("."));
            names[i] = name;
        }
        attributes.put(BASE_PACKAGES, names);
        return this;
    }

    /**
     * set attribute array
     *
     * @param packages
     * @return
     */
    default BaseConfiguration setAttributes(String... packages) {
        attributes.put(BASE_PACKAGES, packages);
        return this;
    }

    /**
     * get attributes array
     *
     * @return
     */
    default String[] getAttributes() {
        return attributes.get(BASE_PACKAGES);
    }

}