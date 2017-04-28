package com.good.www1.win.tools;

import com.good.www1.win.asserts.GeneralCustomException;
import com.good.www1.win.asserts.LoggerWrapper;
import com.good.www1.win.asserts.ScreenCapturingCustomException;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.testng.Assert;

import java.util.Iterator;

/**
 * This class is designed to build some object (image) on the screen.
 */
public final class SikuliPatternWrapper implements ISikuliPatternWrapper {
	private static final String ERROR_IMAGE_NOT_FOUND = "Image not found by path %s";
	private static final String ERROR_IMAGE_FOUND_BUT_SHOULDNT = "Image shouldn't be found! Image should be absent";
	private static final String ERROR_CLICK_ON_PATTERN = "Could not perform click on pattern";
	private static final String ERROR_RIGHTCLICK_ON_PATTERN = "Could not perform right click on pattern";
	private static final String ERROR_TYPE_TEXT_ON_PATTERN = "Could not type text on pattern";
	private static final String RIGHT_SLASH = "/";
	private static final String KEY_A = "a";
	private Pattern pattern;

	private SikuliPatternWrapper(Pattern pattern) {
		this.pattern = pattern;
	}

	public static SikuliPatternWrapper getVisibleElementByRelativePath(String page, String element) {
		return new SikuliPatternWrapper(new Pattern(searchFileName(buildRelativePath(page, element))));
	}

	public static SikuliPatternWrapper getVisibleElementByRelativePath(String page, String element,
			int AccordingToCenterOfPatternByX, int AccordingToCenterOfPatternByY) {
		// Search
		return new SikuliPatternWrapper(new Pattern(searchFileName(buildRelativePath(page, element)))
				.targetOffset(AccordingToCenterOfPatternByX, AccordingToCenterOfPatternByY));
	}

	public static void getNotVisibleElementByRelativePath(String page, String element) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int initialTimeOut = SearchPattern.get().getTimeOut();
		SearchPattern.get().setTimeOut(10);

		try {
			new SikuliPatternWrapper(new Pattern(searchFileName(buildRelativePath(page, element))));
			Assert.fail(ERROR_IMAGE_FOUND_BUT_SHOULDNT);
		} catch (GeneralCustomException e) {
			e.toString();
		} finally {
			SearchPattern.get().setTimeOut(initialTimeOut);
		}
	}

	public static SikuliPatternWrapper getVisibleElementByAbsolutePath(String absolutePath) {
		return new SikuliPatternWrapper(new Pattern(searchFileName(absolutePath)));
	}

	public static SikuliPatternWrapper getVisibleElementByAbsolutePath(String absolutePath,
			int AccordingToCenterOfPatternByX, int AccordingToCenterOfPatternByY) {
		// Search
		return new SikuliPatternWrapper(new Pattern(searchFileName(absolutePath))
				.targetOffset(AccordingToCenterOfPatternByX, AccordingToCenterOfPatternByY));
	}

	private static String buildRelativePath(String page, String element) {
		String relativePath = new String();
		if ((page != null) && (!(page.isEmpty()))) {
			relativePath = relativePath + page + RIGHT_SLASH;
		}
		if ((element != null) && (!(element.isEmpty()))) {
			relativePath = relativePath + element + RIGHT_SLASH;
		}
		return relativePath;
	}

	private static String searchFileName(String path) {
		String fileName = SearchPattern.get().getFileNameOfVisibleElement(path);
		if (fileName.isEmpty()) {
			// Develop custom exception. Image not found.
			throw new GeneralCustomException(String.format(ERROR_IMAGE_NOT_FOUND, path));
		}
		return fileName;
	}

	public void enterKey() {
		SikuliScreenWrapper.get().getCurrentScreen().type(Key.ENTER);
	}

	public void click() {
		try {
			SikuliScreenWrapper.get().getCurrentScreen().click(pattern);
		} catch (FindFailed e) {
			// Develop custom exception.
			throw new ScreenCapturingCustomException(ERROR_CLICK_ON_PATTERN, e);
		}
	}

	public void rightClick() {
		try {
			SikuliScreenWrapper.get().getCurrentScreen().rightClick(pattern);
		} catch (FindFailed e) {
			// Develop custom exception.
			throw new ScreenCapturingCustomException(ERROR_RIGHTCLICK_ON_PATTERN, e);
		}
	}

	public void clear() {
		click();
		SikuliScreenWrapper.get().getCurrentScreen().type(KEY_A, Key.CTRL);
		SikuliScreenWrapper.get().getCurrentScreen().type(Key.DELETE);
	}

	public void typeText(String text) {
		click();
		Settings.TypeDelay = 0.03;
		try {

			SikuliScreenWrapper.get().getCurrentScreen().type(pattern, text);
		} catch (FindFailed e) {
			// Develop custom exception.
			throw new ScreenCapturingCustomException(ERROR_TYPE_TEXT_ON_PATTERN, e);
		}
	}

	public boolean isVisible() {
		return SearchPattern.get().isVisibleElement(pattern);
	}

	public void selectAllText() {

		try {
			SikuliScreenWrapper.get().getCurrentScreen().click(pattern);
		} catch (FindFailed e) {
			// Develop custom exception.
			throw new ScreenCapturingCustomException(ERROR_CLICK_ON_PATTERN, e);
		}
		SikuliScreenWrapper.get().getCurrentScreen().type(KEY_A, Key.CTRL);
	}

	public void moveToElement() {

		try {
			SikuliScreenWrapper.get().getCurrentScreen().hover(pattern);
		} catch (FindFailed e) {
			// Develop custom exception.
			throw new ScreenCapturingCustomException(ERROR_CLICK_ON_PATTERN, e);
		}
		SikuliScreenWrapper.get().getCurrentScreen().type(KEY_A, Key.CTRL);
	}

	public Iterator<Match> findAllItems() {

		Iterator<Match> iterator = null;

		try {
			iterator = SikuliScreenWrapper.get().getCurrentScreen().findAll(pattern);
		} catch (FindFailed findFailed) {
			findFailed.printStackTrace();
		}

		return iterator;
	}

	public void clickAllItems() {
		Iterator<Match> iterator;

		try {
			iterator = SikuliScreenWrapper.get().getCurrentScreen().findAll(pattern);

			Match element = null;
			while (iterator.hasNext()) {
				element = iterator.next();
				element.click();
			}

		} catch (FindFailed e) {
			// Develop custom exception.
			throw new ScreenCapturingCustomException(ERROR_CLICK_ON_PATTERN, e);
		}
		SikuliScreenWrapper.get().getCurrentScreen().type(KEY_A, Key.CTRL);
	}

	public void doubleClick() {
		SikuliScreenWrapper.get().getCurrentScreen().doubleClick();
	}

	public void pasteText(String text) {
		SikuliScreenWrapper.get().getCurrentScreen().paste(text);
		LoggerWrapper.get().infoLog("Paste url: " + text);
	}

	@Override
	public void paste() {
		SikuliScreenWrapper.get().getCurrentScreen().type("v", Key.CTRL);
	}

	@Override
	public void copy() {
		SikuliScreenWrapper.get().getCurrentScreen().type("c", Key.CTRL);
	}

	@Override
	public void quit() {
		SikuliScreenWrapper.get().getCurrentScreen().type(Key.F4, Key.ALT);
	}

	@Override
	public void typeKeyDefiniteTimes(String key, int times) {
		for (int i = 0; i < times; i++) {
			SikuliScreenWrapper.get().getCurrentScreen().type(key);
		}
	}
}
