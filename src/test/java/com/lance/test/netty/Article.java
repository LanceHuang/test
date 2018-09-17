package com.lance.test.netty;

class Article {
    private int totalPage;
    private int currPage;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    @Override
    public String toString() {
        return "Article{" +
                "totalPage=" + totalPage +
                ", currPage=" + currPage +
                '}';
    }
}
