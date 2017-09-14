package swing.downey.latte.ui.refresh;

/**
 * Created by Paul on 2017/9/6.
 */

public class PagingBean {
    //当前是第几页
    private int mPageIndex = 0;
    //总共数据条数
    private int mTotal = 0;
    //一页显示几条数据
    private int mPageSize = 0;
    //当前已经显示了几条数据
    private int mCurrentCount = 0;
    //加载延迟
    private int mDelay = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelay() {
        return mDelay;
    }

    public PagingBean setDelay(int mDelay) {
        this.mDelay = mDelay;
        return this;
    }

    PagingBean addIndex() {
        mPageIndex++;
        return this;
    }
}
