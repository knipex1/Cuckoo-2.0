package com.cuckoo2;

import java.io.Serializable;

import javax.swing.Icon;

public class Program implements Serializable {

	private static final long serialVersionUID = -3183151154684968663L;
	private String name;
	private String executablePath;
	private String command;
	private long scheduledTime;
	private Icon icon;
	private int timesClosed;
	private boolean isSample;

	public Program(String name, String executablePath, String command, long scheduledTime, Icon icon, int timesClosed,
			boolean isSample) {
		this.name = name;
		this.executablePath = executablePath;
		this.command = command;
		this.scheduledTime = scheduledTime;
		this.icon = icon;
		this.timesClosed = timesClosed;
		this.isSample = isSample;
	}

	public Program(String name, String executalePath, Icon icon) {
		this(name, executalePath,
				"TASKKILL /f /im \"" + executalePath.substring(executalePath.lastIndexOf('\\') + 1) + "\"", 0, icon, 0,
				false);
	}

	/**
	 * Sample program
	 */
	public Program(String name, String command, Icon icon, boolean isSample) {
		this(name, "", command, 0, icon, 0, true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExecutablePath() {
		return executablePath;
	}

	public void setExecutablePath(String executablePath) {
		this.executablePath = executablePath;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public long getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(long scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public int getTimesClosed() {
		return timesClosed;
	}

	public void setTimesClosed(int timesClosed) {
		this.timesClosed = timesClosed;
	}

	public boolean isSample() {
		return isSample;
	}

	public void setSample(boolean isSample) {
		this.isSample = isSample;
	}

	public boolean isRunning() {
		return ProgramManager.runningPrograms.contains(this);
	}
}
