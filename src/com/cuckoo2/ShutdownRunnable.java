package com.cuckoo2;

import java.io.IOException;

import com.cuckoo2.swing.CuckooFrame;

public class ShutdownRunnable implements Runnable {
	private int rowInTable;
	private Program program;

	public ShutdownRunnable(int row, Program p) {
		this.program = p;
		this.rowInTable = row;
	}

	@Override
	public void run() {
		try {
			boolean soundPlayed = false;
			while (System.currentTimeMillis() < program.getScheduledTime()) {
				CuckooFrame.getInstance().getProgramsPanel().publishScheduleData(rowInTable);
				// Sound
				if (CuckooFrame.PREFERENCES.getBoolean("SHUTDOWNSOUND", true) && !soundPlayed) {
					if (program.getScheduledTime() - System.currentTimeMillis() <= 60 * 1000) {
						SoundPlayer.playSound(SoundPlayer.SHUTDOWN_SOUND);
						soundPlayed = true;
					}
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// If thread interupted, return
			return;
		}
		CuckooFrame.getInstance().getProgramsPanel().publishScheduleData(rowInTable);
		shutdownProgram();
	}

	private void shutdownProgram() {
		try {
			ProgramManager.shutdownProgram(program);
			CuckooFrame.getInstance().getScheduleShutdownPanel().updateStopButtonVisibility(false);
			if (ProgramManager.runningPrograms.contains(program))
				ProgramManager.runningPrograms.remove(program);
			CuckooFrame.getInstance().getProgramsPanel().publishTableData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
