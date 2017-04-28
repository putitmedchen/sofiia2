package com.good.www1.win.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTimeInSimpleFormat {
	
	   public String getSimpleTime() {
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        return "    Current time = " + sdf.format(cal.getTime());
	    }

}
