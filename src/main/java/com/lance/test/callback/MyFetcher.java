package com.lance.test.callback;

public class MyFetcher implements Fetcher {

    private Data data;

    public MyFetcher(Data data) {
        this.data = data;
    }

    @Override
    public void fetchData(FetcherCallback callback) {
        if (null == data || null == data.getUsername()) {
            callback.onSuccess(data);
        } else {
            callback.onError(new IllegalArgumentException("Invalid data"));
        }
    }
}
