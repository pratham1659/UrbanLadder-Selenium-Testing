package com.book.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggerUtil {
	private static Logger log = LogManager.getLogger(LoggerUtil.class);
	
	public void info(String message) {
        log.info(message);
    }

}
