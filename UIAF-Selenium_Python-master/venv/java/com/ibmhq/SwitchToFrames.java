package com.ibmhq;

import org.openqa.selenium.NoSuchFrameException;

public class SwitchToFrames {
	String strstatus = null;

	/**
	 * <p>
	 * Switches to the sub frames based on the input.
	 * </p>
	 * 
	 * @param String
	 *            Frames separated by delimiter #.
	 * @return void
	 */
	public String SwitchtoSubFramesbyIndex(String Frames) {

		String strstatus = null;
		try {
			String arrFrames[] = null;
			if (Frames != "") {
				if (Frames.contains("#"))
					arrFrames = Frames.split("#");
				else {
					arrFrames = new String[1];
					arrFrames[0] = Frames;
				}

				Browser.Driver.switchTo().defaultContent();
				for (int i = 0; i < arrFrames.length; i++) {
					if (arrFrames[i].chars().allMatch(Character::isDigit))
						Browser.Driver.switchTo().frame(Integer.parseInt(arrFrames[i]));
					else
						Browser.Driver.switchTo().frame(arrFrames[i]);
					strstatus = "Passed$$The driver is switched to Frame: " + arrFrames[i];
				}
			} else
				strstatus = "Failed%%The input is blank. Hence, unable to switch to frames.";

			return strstatus;
		} catch (Exception e) {
			return "Failed%%" + e.getMessage();
		}

	}

	/**
	 * <p>
	 * Switches to the sub frames based on the input.
	 * </p>
	 * 
	 * @param String
	 *            Frames separated by delimiter #.
	 * @return void
	 */
	public String SwitchtoSubFramesbyName(String Frames) {
		int i = 0;
		String arrFrames[] = null;
		try {
			if (Frames.contains("#"))
				arrFrames = Frames.split("#");
			else {
				arrFrames = new String[1];
				arrFrames[0] = Frames;
			}

			Browser.Driver.switchTo().defaultContent();
			for (i = 0; i < arrFrames.length; i++) {
				Browser.Driver.switchTo().frame((arrFrames[i]));
				strstatus = "Passed%%The browser is switched to Frame: " + arrFrames[i];
			}
		} catch (NoSuchFrameException e) {
			strstatus = "Unable to locate frame with name :" + arrFrames[i] + "." + e.toString();
		} catch (Exception e) {
			strstatus = "Failed%%Exception in the method SwitchtoSubFramesbyName:" + e.toString();
		}
		return strstatus;
	}
}
