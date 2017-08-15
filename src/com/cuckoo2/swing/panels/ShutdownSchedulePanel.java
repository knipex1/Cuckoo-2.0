package com.cuckoo2.swing.panels;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.cuckoo2.Icons;
import com.cuckoo2.Program;
import com.cuckoo2.ProgramManager;
import com.cuckoo2.ShutdownRunnable;
import com.cuckoo2.SoundPlayer;
import com.cuckoo2.swing.Button;
import com.cuckoo2.swing.CuckooFrame;

public class ShutdownSchedulePanel extends JPanel {

	private static final long serialVersionUID = 3082882905037764817L;
	private Button startButton, stopButton;
	private JComboBox<String> comboBox;
	private JSpinner spinner;

	public ShutdownSchedulePanel() {
		setBorder(BorderFactory.createTitledBorder("Shutdown Schedule"));
		startButton = new Button("Start", Icons.BUTTON_START_TIME);
		updateStrartButtonAvailability(false);
		stopButton = new Button("Stop Schedule", Icons.BUTTON_STOP_TIME);
		stopButton.setPreferredSize(new Dimension(170, 30));
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(10, 1, 999, 1));
		spinner.setPreferredSize(new Dimension(55, 28));
		spinner.setFont(CuckooFrame.MAIN_FONT);
		spinner.setValue(CuckooFrame.PREFERENCES.getInt("LASTSELECTEDTIME", 10));
		add(spinner);

		comboBox = new JComboBox<String>();
		comboBox.addItem("Seconds");
		comboBox.addItem("Minutes");
		comboBox.addItem("Hours");
		comboBox.setSelectedIndex(CuckooFrame.PREFERENCES.getInt("LASTSELECTEDUNIT", 1)); // minutes default
		comboBox.setFont(CuckooFrame.MAIN_FONT);
		comboBox.setPreferredSize(new Dimension(95, 28));
		add(comboBox);
		add(startButton);
		add(stopButton);
		startButton.addActionListener(e -> {
			TimeUnit timeUnit = TimeUnit.valueOf(((String) (comboBox.getSelectedItem())).toUpperCase());
			long time = System.currentTimeMillis();
			long selectedTime = timeUnit.toMillis((long) ((int) spinner.getValue()));
			time = selectedTime + time;
			int row = CuckooFrame.getInstance().getProgramsPanel().getSelectedRow();
			Program program = CuckooFrame.getInstance().getProgramsPanel().getSelectedProgram();
			program.setScheduledTime(time);
			CuckooFrame.getInstance().getProgramsPanel().publishTableData();
			CuckooFrame.getInstance().getProgramsPanel().selectRow(row);
			Thread t = new Thread(new ShutdownRunnable(row, program));
			t.start();
			ProgramManager.schedules.put(program, t);
			CuckooFrame.PREFERENCES.putInt("LASTSELECTEDTIME", (int) spinner.getValue());
			CuckooFrame.PREFERENCES.putInt("LASTSELECTEDUNIT", comboBox.getSelectedIndex());
			boolean startSound = CuckooFrame.PREFERENCES.getBoolean("STARTSOUND", true) && selectedTime > 60 * 1000;
			if (startSound)
				SoundPlayer.playSound(SoundPlayer.START_SOUND);
			sendTrayNote(selectedTime, program.getName());
		});
		stopButton.addActionListener(e -> {
			int row = CuckooFrame.getInstance().getProgramsPanel().getSelectedRow();
			Program p = CuckooFrame.getInstance().getProgramsPanel().getSelectedProgram();
			Thread t = ProgramManager.schedules.get(p);
			t.interrupt();
			ProgramManager.schedules.remove(p);
			p.setScheduledTime(0);
			CuckooFrame.getInstance().getScheduleShutdownPanel().updateStopButtonVisibility(false);
			CuckooFrame.getInstance().getProgramsPanel().publishTableData();
			CuckooFrame.getInstance().getProgramsPanel().selectRow(row);
		});
		updateStopButtonVisibility(false);
	}

	private void sendTrayNote(long selectedTime, String porgramName) {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("user.timezone"));
		c.setTimeInMillis(selectedTime);
		String hours = c.get(Calendar.HOUR) > 0 ? c.get(Calendar.HOUR) + " Hour(s) and " : "";
		String mins = c.get(Calendar.MINUTE) > 0 ? c.get(Calendar.MINUTE) + " Mins(s) and " : "";
		String secs = c.get(Calendar.SECOND) > 0 ? c.get(Calendar.SECOND) + " Sec(s) and " : "";
		String time = hours + mins + secs;
		time = time.substring(0, time.length() - 5);
		time += ".";
		CuckooFrame.getInstance().trayMessage(porgramName + " will shutdown in " + time, true);
	}

	public void updateStrartButtonAvailability(boolean enable) {
		startButton.setEnabled(enable);
	}

	public void updateStopButtonVisibility(boolean show) {
		stopButton.setVisible(show);
		startButton.setVisible(!show);
		spinner.setVisible(!show);
		comboBox.setVisible(!show);
	}

	public void scheduleProgram(boolean start) {
		if (start)
			startButton.doClick();
		else
			stopButton.doClick();
	}
}
