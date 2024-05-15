package com.manage.common;

import lombok.Data;

/**
 * @author lyq
 * @time 2024/1/25 10:50
 * hello a
 */

@Data
public class Meta {
    private String msg;
    private Integer status;

    public Meta(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }

    public Meta() {
    }
}
