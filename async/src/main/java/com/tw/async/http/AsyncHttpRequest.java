package com.tw.async.http;


import com.tw.async.AsyncSSLException;
import com.tw.async.http.body.AsyncHttpRequestBody;
import com.tw.async.hilog.Logger;
import ohos.utils.net.Uri;

import java.util.Locale;

public class AsyncHttpRequest {
    public RequestLine getRequestLine() {
        return new RequestLine() {
            @Override
            public String getUri() {
                return AsyncHttpRequest.this.getUri().toString();
            }
            
            @Override
            public ProtocolVersion getProtocolVersion() {
                return new ProtocolVersion("HTTP", 1, 1);
            }
            
            @Override
            public String getMethod() {
                return mMethod;
            }

            @Override
            public String toString() {
                if (proxyHost != null)
                    return String.format(Locale.ENGLISH, "%s %s %s", mMethod, AsyncHttpRequest.this.getUri(), requestLineProtocol);
                String path = getPath();
                if (path == null || path.length() == 0)
                    path = "/";
                String query = AsyncHttpRequest.this.getUri().getEncodedQuery();
                if (query != null && query.length() != 0) {
                    path += "?" + query;
                }
                return String.format(Locale.ENGLISH, "%s %s %s", mMethod, path, requestLineProtocol);
            }
        };
    }

    public boolean hasBody() {
        return true;
    }

    public String getPath() {
        return AsyncHttpRequest.this.getUri().getEncodedPath();
    }

    protected static String getDefaultUserAgent() {
        String agent = System.getProperty("http.agent");
        return agent != null ? agent : ("Java" + System.getProperty("java.version"));
    }

    private String requestLineProtocol = "HTTP/1.1";
    private String mMethod;
    public String getMethod() {
       return mMethod; 
    }

    public void setRequestLineProtocol(String scheme) {
        this.requestLineProtocol = scheme;
    }

    public String getRequestLineProtocol() {
        return requestLineProtocol;
    }

    public AsyncHttpRequest setMethod(String method) {
        if (getClass() != AsyncHttpRequest.class)
            throw new UnsupportedOperationException("can't change method on a subclass of AsyncHttpRequest");
        mMethod = method;
        return this;
    }

    public AsyncHttpRequest(Uri uri, String method) {
        this(uri, method, null);
    }

    public static void setDefaultHeaders(Headers ret, Uri uri) {
        if (uri != null) {
//            String host = uri.getHost();
            String host = uri.getDecodedHost();
            if (uri.getPort() != -1)
                host = host + ":" + uri.getPort();
            if (host != null)
                ret.set("Host", host);
        }
        ret.set("User-Agent", getDefaultUserAgent());
        ret.set("Accept-Encoding", "gzip, deflate");
        ret.set("Connection", "keep-alive");
        ret.set("Accept", HEADER_ACCEPT_ALL);
    }

    public static final String HEADER_ACCEPT_ALL = "*/*";

    public AsyncHttpRequest(Uri uri, String method, Headers headers) {
        assert uri != null;
        mMethod = method;
        this.uri = uri;
        if (headers == null)
            mRawHeaders = new Headers();
        else
            mRawHeaders = headers;
        if (headers == null)
            setDefaultHeaders(mRawHeaders, uri);
    }

    Uri uri;
    public Uri getUri() {
        return uri;
    }
    
    private Headers mRawHeaders = new Headers();

    public Headers getHeaders() {
        return mRawHeaders;
    }

    private boolean mFollowRedirect = true;
    public boolean getFollowRedirect() {
        return mFollowRedirect;
    }
    public AsyncHttpRequest setFollowRedirect(boolean follow) {
        mFollowRedirect = follow;
        return this;
    }
    
    private AsyncHttpRequestBody mBody;
    public void setBody(AsyncHttpRequestBody body) {
        mBody = body;
    }
    
    public AsyncHttpRequestBody getBody() {
        return mBody;
    }
    
    public void onHandshakeException(AsyncSSLException e) {
    }

    public static final int DEFAULT_TIMEOUT = 30000;
    int mTimeout = DEFAULT_TIMEOUT;
    public int getTimeout() {
        return mTimeout;
    }
    
    public AsyncHttpRequest setTimeout(int timeout) {
        mTimeout = timeout;
        return this;
    }

    public AsyncHttpRequest setHeader(String name, String value) {
        getHeaders().set(name, value);
        return this;
    }

    public AsyncHttpRequest addHeader(String name, String value) {
        getHeaders().add(name, value);
        return this;
    }

    String proxyHost;
    int proxyPort = -1;
    public void enableProxy(String host, int port) {
        proxyHost = host;
        proxyPort = port;
    }

    public void disableProxy() {
        proxyHost = null;
        proxyPort = -1;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    @Override
    public String toString() {
        if (mRawHeaders == null)
            return super.toString();
        return mRawHeaders.toPrefixString(uri.toString());
    }

    public void setLogging(String tag, int level) {
        LOGTAG = tag;
        logLevel = level;
    }
    // request level logging
    String LOGTAG;
    int logLevel;
    public int getLogLevel() {
        return logLevel;
    }
    public String getLogTag() {
        return LOGTAG;
    }
    long executionTime;
    private String getLogMessage(String message) {
        long elapsed;
        if (executionTime != 0)
            elapsed = System.currentTimeMillis() - executionTime;
        else
            elapsed = 0;
        return String.format(Locale.ENGLISH, "(%d ms) %s: %s", elapsed, getUri(), message);
    }
    public void logi(String message) {
        if (LOGTAG == null)
            return;
        if (logLevel > Logger.INFO)
            return;
        Logger.i(LOGTAG, getLogMessage(message));
    }
    public void logv(String message) {
        if (LOGTAG == null)
            return;
        if (logLevel > Logger.VERBOSE)
            return;
        Logger.v(LOGTAG, getLogMessage(message));
    }
    public void logw(String message) {
        if (LOGTAG == null)
            return;
        if (logLevel > Logger.WARN)
            return;
        Logger.w(LOGTAG, getLogMessage(message));
    }
    public void logd(String message) {
        if (LOGTAG == null)
            return;
        if (logLevel > Logger.DEBUG)
            return;
        Logger.d(LOGTAG, getLogMessage(message));
    }
    public void logd(String message, Exception e) {
        if (LOGTAG == null)
            return;
        if (logLevel > Logger.DEBUG)
            return;
        Logger.d(LOGTAG, getLogMessage(message));
        Logger.d(LOGTAG, e.getMessage(), e);
    }
    public void loge(String message) {
        if (LOGTAG == null)
            return;
        if (logLevel > Logger.ERROR)
            return;
        Logger.e(LOGTAG, getLogMessage(message));
    }
    public void loge(String message, Exception e) {
        if (LOGTAG == null)
            return;
        if (logLevel > Logger.ERROR)
            return;
        Logger.e(LOGTAG, getLogMessage(message));
        Logger.e(LOGTAG, e.getMessage(), e);
    }
}
