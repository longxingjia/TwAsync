package com.tw.async.callback;


import com.tw.async.AsyncServerSocket;
import com.tw.async.AsyncSocket;

public interface ListenCallback extends CompletedCallback {
    public void onAccepted(AsyncSocket socket);
    public void onListening(AsyncServerSocket socket);
}
