package com.good.www1.win.tests.installation;

import com.good.www1.win.testNG.TestNGListener;
import com.good.www1.win.utils.BuildUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

@Listeners(TestNGListener.class)
public class GetBuildTest {

    private String mappedDrivePath = "Z:";
    private String lastBuildNumber;
    private String destinationFolderPath;
    private File currentBuildDirectory;
    private File destinationBuildDirectory;

    private BuildUtils buildUtils = new BuildUtils();


    @Test(description = "this test copies build from network directory to local directory('C:\\Users\\currentUserName\\GoodAccess\\builds\\')")
    public void getBuild() {
        lastBuildNumber = buildUtils.getCurrentBuildNumberLocal(mappedDrivePath);
        destinationFolderPath = buildUtils.getBuildsDestinationPath() + lastBuildNumber;

        destinationBuildDirectory = new File(destinationFolderPath);

        currentBuildDirectory = buildUtils.getBuildByNumber(mappedDrivePath, lastBuildNumber);

        buildUtils.copyBuild(currentBuildDirectory, destinationBuildDirectory);
    }

}
