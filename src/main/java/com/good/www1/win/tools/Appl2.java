package com.good.www1.win.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

public class Appl2 {
    public static void main(String[] args) throws IOException {
        Screen screen = new Screen();
        ScreenImage screenImage = screen.capture(screen.getBounds());
        // Shutil shutil.move(img, os.path.join(screenshotsDir,
        // "some-name.png"))
        System.out.println("Saved screen as " + screenImage.getFile());
        BufferedImage image = screenImage.getImage();
        ImageIO.write(image, "png", new File("C:\\Temp\\aaa.png"));
    }
}
