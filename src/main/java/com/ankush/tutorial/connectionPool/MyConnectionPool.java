package com.ankush.tutorial.connectionPool;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyConnectionPool implements ConnectionPool {

    private static MyConnectionPool instance;
    private static int maxPoolSize;
    private static ConcurrentHashMap<Long, Connection> usedPool = new ConcurrentHashMap<>();

    private MyConnectionPool(int poolSize) {
        this.maxPoolSize = poolSize;
    }

    public static MyConnectionPool getInstance(int poolSize){
        if (instance == null) {
            return new MyConnectionPool(poolSize);
        } else {
            return instance;
        }
    }

    @Override
     synchronized public Connection getConnection() throws Exception {
        Double m;

            Long currThread = Thread.currentThread().getId();
            if (usedPool.containsKey(currThread)) return usedPool.get(currThread);
            System.out.println("used pool size = " + usedPool.size());
            if (usedPool.size() == maxPoolSize) throw new Exception("max size exceeded");

            Connection newConnection = new Connection() {
                @Override
                public void execute() throws Exception {
                    if (usedPool.containsKey(currThread)) {
                        System.out.println("executing connection : "+ currThread);
                    } else {
                        throw new Exception("connection does not exist...");
                    }
                }

                @Override
                public void release() {
                    System.out.println("removing thread = "+currThread);
                    usedPool.remove(currThread);
                }
            };
            usedPool.put(currThread, newConnection);
            System.out.println("thread id = "+ currThread);
            System.out.println("used pool size = "+usedPool.size());
            return newConnection;

    }
}
