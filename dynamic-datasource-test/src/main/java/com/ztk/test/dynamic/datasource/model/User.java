/*
 * description
 */
package com.ztk.test.dynamic.datasource.model;

/**
 * 类的描述
 *
 * @author sunyue
 * @date 2020/8/5 下午8:00
 */
public class User {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
