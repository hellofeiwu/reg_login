package com.imooc.utils;

import javax.servlet.http.Cookie;

/**
 * Created by FWU31 on 12/20/2018.
 */
public class CookieUtils {
    public static Cookie getCookie(Cookie[] cookies, String name) {
        if(cookies == null) {
            return null;
        }else {
            for(Cookie item : cookies) {
                if(name.equals(item.getName())) {
                    return item;
                }
            }
            return null;
        }
    }
}
