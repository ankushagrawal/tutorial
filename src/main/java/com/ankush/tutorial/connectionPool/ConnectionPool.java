package com.ankush.tutorial.connectionPool;

public interface ConnectionPool {

    Connection getConnection() throws Exception;
}
