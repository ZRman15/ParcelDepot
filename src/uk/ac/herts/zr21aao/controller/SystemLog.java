package uk.ac.herts.zr21aao.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SystemLog {

	private static final SystemLog INSTANCE = new SystemLog();

	private final List<String> logs = new ArrayList<>();

	private SystemLog() {
	}

	public static SystemLog getInstance() {
		return INSTANCE;
	}

	public void log(String message) {
		String timestamp = LocalDateTime.now().toString();
		logs.add("[" + timestamp + "] " + message);
	}

	public void writeLogsToFile(String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			for (String entry : logs) {
				writer.write(entry);
				writer.newLine();
			}
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearLogs() {
		logs.clear();
	}
}