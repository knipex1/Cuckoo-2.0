package com.cuckoo2;

import javax.swing.Icon;

public enum SampleProgram {
	PCSHUTDOWN("PC Shutdown", "shutdown /s /t 0", Icons.SAMPLE_PROGRAM_PCSHUTDOWN),
	CHROME("Google Chrome", "TASKKILL /f /im chrome.exe", Icons.SAMPLE_PROGRAM_CHROME),
	FIREFOX("Mozilla Firefox", "TASKKILL /f /im firefox.exe", Icons.SAMPLE_PROGRAM_FIREFOX),
	WINDOWSMEDIAPLAYER("Windows Media Player", "TASKKILL /f /im wmplayer.exe", Icons.SAMPLE_PROGRAM_WMP),
	SPOTIFY("Spotify", "TASKKILL /f /im Spotify.exe", Icons.SAMPLE_PROGRAM_SPOTIFY);

	private String name, command;
	private Icon icon;

	private SampleProgram(String name, String command, Icon icon) {
		this.name = name;
		this.command = command;
		this.icon = icon;
	}

	public Program toProgram() {
		return new Program(name, command, icon, true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

}
