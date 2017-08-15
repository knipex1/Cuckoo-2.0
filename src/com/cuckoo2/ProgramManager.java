package com.cuckoo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class ProgramManager {
	private static ArrayList<Program> programs = new ArrayList<Program>();
	public static final HashMap<Program, Thread> schedules = new HashMap<>();
	public static final ArrayList<Program> runningPrograms = new ArrayList<Program>();
	private static final String PROPERTIES_LOCATION = System.getenv("APPDATA") + "\\Cuckoo.tmp";

	public static void Initialize() {
		loadPrograms();
		unScheduleAllPrograms();
		reOrder();
		updatePrefs();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new ReadRunningProgramsTask(), 500, ReadRunningProgramsTask.REPEAT_SECONDS * 1000);
	}

	@SuppressWarnings("unchecked")
	private static void loadPrograms() {
		try {
			File f = new File(PROPERTIES_LOCATION);
			if (!f.exists())
				return;
			FileInputStream fis;
			fis = new FileInputStream(PROPERTIES_LOCATION);
			ObjectInputStream ois = new ObjectInputStream(fis);
			programs = (ArrayList<Program>) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private static void unScheduleAllPrograms() {
		for (Program p : programs) {
			p.setScheduledTime(0);
		}
	}

	private static void reOrder() {
		programs.sort((Program p, Program p2) -> p2.getTimesClosed() - p.getTimesClosed());
	}

	public static void updatePrefs() {
		try {
			FileOutputStream fos = new FileOutputStream(PROPERTIES_LOCATION);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(programs);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Can't update prefs");
		}
	}

	public static ArrayList<Program> getPrograms() {
		return programs;
	}

	public static void addProgram(Program p) {
		programs.add(p);
		updatePrefs();
	}

	public static void removeProgram(Program p) {
		programs.remove(p);
		reOrder();
		updatePrefs();
	}

	public static void shutdownProgram(Program program) throws IOException {
		Runtime.getRuntime().exec(program.getCommand());
		program.setTimesClosed(program.getTimesClosed() + 1);
		ProgramManager.schedules.remove(program);
		reOrder();
		updatePrefs();
	}

	public static boolean hasSample(SampleProgram sample) {
		for (Program p : programs) {
			if (p.isSample() && p.getCommand().equals(sample.getCommand()))
				return true;
		}
		return false;
	}
}