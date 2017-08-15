package com.cuckoo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TimerTask;

import com.cuckoo2.swing.CuckooFrame;

public class ReadRunningProgramsTask extends TimerTask {
	public static final int REPEAT_SECONDS = 20;

	@Override
	public void run() {
		ProgramManager.runningPrograms.clear();
		String total = null;
		String line;
		try {
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				total += line;
				total += "\n";
			}
		} catch (IOException e) {

		}
		total = total.toLowerCase();
		for (Program p : ProgramManager.getPrograms()) {
			String exe = p.getCommand().substring("TASKKILL /f /im ".length(), p.getCommand().length());
			exe = exe.replaceAll("\"", "");
			exe = exe.toLowerCase();
			if (total.contains(exe)) {
				ProgramManager.runningPrograms.add(p);
			}
		}
		int row = CuckooFrame.getInstance().getProgramsPanel().getSelectedRow();
		CuckooFrame.getInstance().getProgramsPanel().publishTableData();
		if (row >= 0)
			CuckooFrame.getInstance().getProgramsPanel().selectRow(row);
	}

}
