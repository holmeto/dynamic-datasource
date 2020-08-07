/*
 * description
 */
package com.ztk.test.dynamic.datasource.controller;

import com.ztk.dynamic.datasource.route.DynamicDataSourceContextHolder;
import com.ztk.test.dynamic.datasource.model.User;
import com.ztk.test.dynamic.datasource.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/test1")
    public User test1() {
        return testService.test1();
    }

    @GetMapping("/test2")
    public User test2() {
        return testService.test2();
    }

    @GetMapping("/test3")
    public User test3() {
        return testService.test3();
    }

    @GetMapping("/test4")
    public User test4(@RequestParam("id") int id) {
        return testService.test4(id);
    }
}
