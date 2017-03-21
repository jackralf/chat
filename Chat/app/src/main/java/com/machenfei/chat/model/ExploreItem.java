package com.machenfei.chat.model;

/**
 * Created by machenfei on 2017/3/21.
 */

public class ExploreItem {
    public int iconId;
    public int nameId;
    private boolean isHeader;
    private boolean isFooter;

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isFooter() {
        return isFooter;
    }

    public void setFooter(boolean footer) {
        isFooter = footer;
    }
}
