package com.cuckoo2;

import javax.swing.Icon;

public interface Icons {
	// Button Icons
	Icon BUTTON_ADD_PROGRAM = IconLoader.loadIcon("button_add_program");
	Icon BUTTON_EDIT_PROGRAM = IconLoader.loadIcon("button_edit_program");
	Icon BUTTON_REMOVE_PROGRAM = IconLoader.loadIcon("button_remove_program");
	Icon BUTTON_START_TIME = IconLoader.loadIcon("button_start_time");
	Icon BUTTON_STOP_TIME = IconLoader.loadIcon("button_stop_time");
	// Sample Programs Icons
	Icon SAMPLE_PROGRAM_PCSHUTDOWN = IconLoader.loadIcon("sample_shutdown.png", 32, 32);
	Icon SAMPLE_PROGRAM_CHROME = IconLoader.loadIcon("sample_chrome.png", 32, 32);
	Icon SAMPLE_PROGRAM_FIREFOX = IconLoader.loadIcon("sample_firefox.png", 32, 32);
	Icon SAMPLE_PROGRAM_WMP = IconLoader.loadIcon("sample_wmp.png", 32, 32);
	Icon SAMPLE_PROGRAM_SPOTIFY = IconLoader.loadIcon("sample_spotify.png", 32, 32);
	// Table Icons
	Icon TABLE_HEADER_PROGRAM = IconLoader.loadIcon("table_program.png");
	Icon TABLE_HEADER_TIMESCLOSED = IconLoader.loadIcon("table_times_closed.png");
	Icon TABLE_HEADER_SCHEDULE = IconLoader.loadIcon("table_schedule.png");
	Icon TABLE_HEADER_RUNNING = IconLoader.loadIcon("table_running.png");
	Icon TABLE_PROGRAM_NOT_SCHEDULED = IconLoader.loadIcon("table_noschedule.png", 32, 32);
	Icon TABLE_PROGRAM_NOT_RUNNING = IconLoader.loadIcon("table_program_notrunning.png", 25, 25);
	Icon TABLE_PROGRAM_RUNNING = IconLoader.loadIcon("table_program_running.png", 25, 25);
	// Menu Icons
	Icon MENU_STANDARD_PROGRAM = IconLoader.loadIcon("menu_standard_program.png");
	Icon MENU_SETTINGS = IconLoader.loadIcon("menu_settings.png");
}
