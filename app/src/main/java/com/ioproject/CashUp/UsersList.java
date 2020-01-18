package com.ioproject.CashUp;

/**
 * This class is used to assign values in RecyclerView
 */
public class UsersList {
    private int mImageResource;
    private String mText1;
    private String mText2;

    /**
     * This method allows you to set the text in RecyclerView
     * @param imageResource Set image
     * @param text1 Set text1
     * @param text2 Set text2
     */
    public UsersList(int imageResource, String text1, String text2) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    /**
     * This method sets text1
     * @return text1
     */
    public void changeText1(String text) {
        mText1 = text;
    }

    /**
     * This method gets image
     * @return image
     */
    public int getImageResource() {
        return mImageResource;
    }

    /**
     * This method gets text1
     * @return text1
     */
    public String getText1() {
        return mText1;
    }

    /**
     * This method gets text2
     * @return text2
     */
    public String getText2() {
        return mText2;
    }
}