package com.example.boot.springdata.util;

import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;

public class JDBCUtilTest {

    @Test
    public void testGetConnection() throws Exception {
        Connection connection = JdbcUtil.getConnection();
        Assert.assertNotNull(connection);
    }

}
