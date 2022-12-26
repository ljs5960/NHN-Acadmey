package com.nhnacademy.jdbc.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
'~/' 요청시 '~/board' 로 리다이렉트
 */

@Controller
public class IndexController {
    @GetMapping("")
    public String index() {
        return "redirect:/board";
    }
}
