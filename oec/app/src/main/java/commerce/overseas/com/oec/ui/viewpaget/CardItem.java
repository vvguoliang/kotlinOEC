package commerce.overseas.com.oec.ui.viewpaget;


public class CardItem {

    private String mTextResource1;
    private String mTitleResource1;
    private String mTextResource2;
    private String mTitleResource2;

    public CardItem(String title1, String url1, String title2, String url2) {
        mTitleResource1 = title1;
        mTextResource1 = url1;
        mTitleResource2 = title2;
        mTextResource2 = url2;
    }

    public String getmTextResource1() {
        return mTextResource1;
    }

    public void setmTextResource1(String mTextResource1) {
        this.mTextResource1 = mTextResource1;
    }

    public String getmTitleResource1() {
        return mTitleResource1;
    }

    public void setmTitleResource1(String mTitleResource1) {
        this.mTitleResource1 = mTitleResource1;
    }

    public String getmTextResource2() {
        return mTextResource2;
    }

    public void setmTextResource2(String mTextResource2) {
        this.mTextResource2 = mTextResource2;
    }

    public String getmTitleResource2() {
        return mTitleResource2;
    }

    public void setmTitleResource2(String mTitleResource2) {
        this.mTitleResource2 = mTitleResource2;
    }
}
