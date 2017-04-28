package com.good.www1.win.tools;

import org.sikuli.script.Screen;

/**
 * Class designed for wrapping screen using Sikuli tool.
 */
public final class SikuliScreenWrapper {
	/*
	 * Classic implementation of singletone pattern for creation of Sikuli
	 * screen.
	 */
	private static volatile SikuliScreenWrapper instance = null;
	private static volatile Screen screen = null;

	/*
	 * Classic implementation of singletone pattern for creation of Sikuli
	 * screen. We need to create default empty constructor not to allow compiler
	 * to create hit own constructor which will be public and which breaks logic
	 * of singletone as it should allow to create just one object.
	 */
	private SikuliScreenWrapper() {
	}

	/*
	 * Classic implementation of singletone pattern for creation of Sikuli
	 * screen.
	 */
	public static SikuliScreenWrapper get() {
		//if (instance == null) {
			 if (true) {
			synchronized (SikuliScreenWrapper.class) {
				//if (instance == null) {
					 if (true) {
					instance = new SikuliScreenWrapper();
				}
			}
		}
		return instance;
	}

	Screen getCurrentScreen() {
		if (screen == null) {
			synchronized (SikuliScreenWrapper.class) {
				if (screen == null) {
					screen = new Screen();
				}
			}
		}
		return screen;
	}

	public void resetScreen() {
		screen = null;
	}

}
