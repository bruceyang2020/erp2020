package cn.edu.hdu.clan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author clan
 * @function
 * @date 2018/5/27.
 */
public class BaseController {
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 400;
    private static final String CODE = "code";
    private static final String MSG = "msg";
    private static final String DATA = "data";

    protected String success(String msg, Object o) {
        Map<String, Object> map = new HashMap<>();
        map.put(CODE, CODE_SUCCESS);
        map.put(MSG, msg);
        map.put(DATA, o);
        return convert2Json(map);
    }

    protected String success(String msg) {
        return success(msg, null);
    }

    protected String success(Object o) {
        if (o==null){
            return success();
        }
        return success(null, o);
    }

    protected String success() {
        return success(null, null);
    }

    protected String error(String msg, Object o) {
        Map<String, Object> map = new HashMap<>();
        map.put(CODE, CODE_ERROR);
        map.put(MSG, msg);
        map.put(DATA, o);
        return convert2Json(map);
    }

    protected String error(String msg) {
        return error(msg, null);
    }

    protected String error(Object o) {
        return error(null, o);
    }

    protected String convert2Json(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
}

