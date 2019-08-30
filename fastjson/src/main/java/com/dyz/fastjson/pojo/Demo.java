package com.dyz.fastjson.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author dyz
 * @program boot-use
 * @create 2019-08-09 16:56
 */
@Data
public class Demo {
    private int id;
    private String name;
    /**
     * com.alibaba.fastjson.annotation.JSONField
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     * 我们不想返回remarks?
     * serialize:是否需要序列化属性.
     */
    @JSONField(serialize = false)
    private String remarks;
}
