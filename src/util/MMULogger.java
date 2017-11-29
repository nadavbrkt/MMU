package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MMULogger {
	final static String DEFAULT_FILE_NAME = "log.txt";
	private FileHandler handler;
	private static final MMULogger instance = new MMULogger();
	
	private MMULogger(){
		try {
			handler = new FileHandler(DEFAULT_FILE_NAME);
			handler.setFormatter(new OnlyMessageFormatter());
		} catch (IOException e) {
			System.err.println("Cannot create handler " + e.getMessage());
		}
	}
	
	public static MMULogger getInstance(){
		return instance;
	}
	
	public void write(String command, Level level) {
		handler.publish(new LogRecord(level,command));
	}
	
	public void close(){
		handler.close();
	}
	
	public class OnlyMessageFormatter extends Formatter{
		public OnlyMessageFormatter() { super(); }

		@Override
		public String format(final LogRecord record) {
			// TODO Auto-generated method stub
			return record.getMessage();
		}
		
	}
}
