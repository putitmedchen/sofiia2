package com.good.www1.win.functionality.otherApps;

import com.good.www1.win.pages.otherapps.ciscovpn.CiscoVPNWindowPage;

import java.io.File;
import java.io.IOException;

public class CiscoVPNFunctionality {

    private CiscoVPNWindowPage ciscoVPNWindowPage;

    public void clickOnDisconnectButton() {
        ciscoVPNWindowPage = new CiscoVPNWindowPage();
        ciscoVPNWindowPage.clickOnDisconnectButton();
    }

    public void disconnectVpnViaCmd() {

        String vpnDirPath = "C:\\Program Files (x86)\\Cisco\\Cisco AnyConnect Secure Mobility Client";
        String[] disconnectCommand = new String[]{"cmd.exe", "/c", "vpncli", "disconnect"};

        File vpnDir = new File(vpnDirPath);

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(vpnDir);

        processBuilder.command(disconnectCommand);

        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

