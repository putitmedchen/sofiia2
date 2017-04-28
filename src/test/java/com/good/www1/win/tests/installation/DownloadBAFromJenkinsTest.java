package com.good.www1.win.tests.installation;

import com.good.www1.win.utils.BuildUtils;
import org.testng.annotations.Test;

public class DownloadBAFromJenkinsTest {

    private BuildUtils buildUtils = new BuildUtils();

    @Test
    public void downloadBA() {
        buildUtils.downloadBA();
    }

    @Test
    public void downloadBWork() {
        buildUtils.downloadBWork();
    }
}
