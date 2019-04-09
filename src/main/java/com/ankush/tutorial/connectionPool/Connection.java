package com.ankush.tutorial.connectionPool;

public interface Connection {
    void execute() throws Exception;
    void release() throws Exception;
}
