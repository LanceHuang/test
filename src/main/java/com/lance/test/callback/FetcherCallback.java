package com.lance.test.callback;

public interface FetcherCallback {

    void onSuccess(Data data);

    void onError(Throwable t);
}
