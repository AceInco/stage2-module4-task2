package com.mjc.stage2.impl;


import com.mjc.stage2.Connection;

public class ProxyConnection implements Connection {
    private ConnectionPool connectionPool;
    private RealConnection realConnection;

    public ProxyConnection(RealConnection realConnection) {
        this.realConnection = realConnection;

    }

    public void reallyClose() {
        close();
        realConnection.close();
    }

    @Override
    public void close() {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.releaseConnection(realConnection);
    }

    @Override
    public boolean isClosed() {
        return realConnection.isClosed();
    }
    // Implement methods here!
}
