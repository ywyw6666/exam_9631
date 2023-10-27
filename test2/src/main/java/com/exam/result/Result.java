package com.exam.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer code; //编码：0成功，其它数字为失败
    private String msg; //信息
    private T result; //数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 0;
        result.msg = "ok";
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.result = object;
        result.code = 0;
        result.msg = "ok";
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 1;
        return result;
    }

}
