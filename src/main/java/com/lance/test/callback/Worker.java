package com.lance.test.callback;

public class Worker {

    public void doWork() {
        Fetcher fetcher = new MyFetcher(new Data("lance", 24));
        fetcher.fetchData(new FetcherCallback() {
            @Override
            public void onSuccess(Data data) {
                System.out.println("Data: " + data);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new Worker().doWork();
    }
}
