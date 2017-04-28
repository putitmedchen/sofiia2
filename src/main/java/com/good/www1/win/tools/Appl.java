package com.good.www1.win.tools;

import java.io.File;

import com.good.www1.win.asserts.GeneralCustomException;

public class Appl {
    public static void main(String[] args) throws InterruptedException {
        // System.out.println("path= "+new
        // Appl().getClass().getResourceAsStream("/a.properties").toString());
        String path = Appl.class.getResource("/").toString().substring(6);
        System.out.println("path= " + path);
        System.out.println("is : " + (path.indexOf(":") > 0));
        System.out.println("empty : " + (new String().indexOf(":") > 0));
        File folder = new File(path);

        for (File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                System.out.println(fileEntry.getName());
            }
        }
        //
        long currentTime = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println("res=" + (System.currentTimeMillis() - currentTime));
        //
        System.out.println("Generate Exception:");
        throw new GeneralCustomException("Generate Exception");
    }

}
