package com.ankush.tutorial.connectionPool;

public class Client implements Runnable {
    static MyConnectionPool connectionPool = MyConnectionPool.getInstance(4);

    public Client() {
    }

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new Client());
        Thread t2 = new Thread(new Client());
        Thread t3 = new Thread(new Client());
        Thread t4 = new Thread(new Client());
        Thread t5 = new Thread(new Client());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

    @Override
    public void run() {
        try {
            Connection connection1 = connectionPool.getConnection();
            connection1.execute();
            Thread.sleep(20);
            connection1.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
