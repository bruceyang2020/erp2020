package cn.edu.hdu.clan.config;

import cn.edu.hdu.clan.controller.BaseController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常捕获器
 */
@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler extends BaseController {
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        e.printStackTrace();
        return error(e.getMessage());
    }
}