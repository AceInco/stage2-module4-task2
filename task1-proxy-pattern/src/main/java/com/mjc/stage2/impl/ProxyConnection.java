package com.mjc.stage2.impl;


import com.mjc.stage2.Connection;

public class ProxyConnection implements Connection {
    private ConnectionPool connectionPool;
    private RealConnection realConnection;

    public ProxyConnection(RealConnection realConnection) {
        this.realConnection = realConnection;
        this.connectionPool = ConnectionPool.getInstance();
    }

    public void reallyClose() {
        realConnection.close();
        close();
    }

    @Override
    public void close() {
        connectionPool.releaseConnection(realConnection);
    }

    @Override
    public boolean isClosed() {
        return realConnection.isClosed();
    }
    // Implement methods here!
}
