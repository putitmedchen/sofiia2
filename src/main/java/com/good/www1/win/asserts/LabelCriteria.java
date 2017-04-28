package com.good.www1.win.asserts;

import com.good.www1.win.tools.SearchPattern;

public class LabelCriteria {
    private static final String WHEN_LABEL_NOT_VISIBLE = "Label it's not visible";
    private String path;
    private AssertWrapper assertWrapper;

    private LabelCriteria(String path, AssertWrapper assertWrapper) {
        this.path = path;
        this.assertWrapper = assertWrapper;
    }

    public static LabelCriteria get(String path, AssertWrapper assertWrapper) {
        return new LabelCriteria(path, assertWrapper);
    }

    public LabelCriteria isVisible() {
        assertWrapper.verify(SearchPattern.get().isVisibleElement(path),
                WHEN_LABEL_NOT_VISIBLE);
        return this;
    }

    public AssertWrapper next() {
        return assertWrapper;
    }

}
