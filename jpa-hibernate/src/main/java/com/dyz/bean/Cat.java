package com.dyz.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 如何持久化呢？
 * 1、使用@Entity进行实体类的持久化操作，当JPA检测到我们的实体类当中有
 *
 * @author dyz
 * @entity 注解的时候，会在数据库中生成对应的表结构信息。
 * 如何指定主键以及主键的生成策略？
 * 2、使用@Id指定主键.
 */
@Entity
@Data
public class Cat {

    /**
     * 使用@Id指定主键.
     * <p>
     * 使用代码@GeneratedValue(strategy=GenerationType.AUTO)
     * 指定主键的生成策略，mysql默认的是自增长。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String catName;

    private int catAge;
}
