/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;
import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Event;

/**
 * The WolfScheduler Class manages a course catalog and schedule.
 * 
 * @author Anoushka Piduru
 */
public class WolfScheduler {

	/** A list of courses available in the catalog. */
	private ArrayList<Course> catalog;
	/** A list of courses in the schedule. */
	private ArrayList<Activity> schedule;
	/** The title of the schedule. */
	private String title;

	/**
	 * Constructs a WolfScheduler object with a file holding course records.
	 * 
	 * @param fileName the file with the course records being read.
	 * @throws IllegalArgumentException is file is not found.
	 */
	public WolfScheduler(String fileName) {
		schedule = new ArrayList<>();
		title = "My Schedule";
		catalog = new ArrayList<>();

		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Forms a 2D String array of the catalog.
	 * 
	 * @return String[][] a 2D String array of the catalog.
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		return catalogArray;
	}

	/**
	 * Forms a 2D String array of the schedule.
	 * 
	 * @return String[][] a 2D String array of the schedule.
	 */
	public String[][] getScheduledActivities() {
		if (schedule == null) {
			return new String[0][0];
		}

		String[][] scheduledActivities = new String[schedule.size()][3];

		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			scheduledActivities[i] = a.getShortDisplayArray();
		}

		return scheduledActivities;
	}

	/**
	 * Creates a 2D String array of the full scheduled courses.
	 * 
	 * @return String[][] a 2D String array representing the full schedule.
	 */
	public String[][] getFullScheduledActivities() {
		if (schedule == null) {
			return new String[0][0];
		}

		String[][] fullScheduledActivities = new String[schedule.size()][6];

		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			fullScheduledActivities[i] = a.getLongDisplayArray();
		}

		return fullScheduledActivities;

	}

	/**
	 * Returns the title of the schedule.
	 * 
	 * @return the title of the schedule.
	 */
	public String getScheduleTitle() {
		return title;
	}

	/**
	 * Exports the schedule into a file.
	 * 
	 * @param fileName the file which the schedule will be exported into.
	 * @throws IllegalArgumentException if the file cannot be saved.
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

	/**
	 * Gets a specified course from the catalog given a name and section
	 * 
	 * @param name    of course to get
	 * @param section of course to get
	 * @return The course if found, null if not
	 */
	public Course getCourseFromCatalog(String name, String section) {
		if (catalog == null) {
			return null;
		}
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Adds a course to schedule
	 * 
	 * @param name    of the course to add
	 * @param section of the course to add
	 * @return boolean
	 * @throws IllegalArgumentException if course is already in schedule
	 */
	public boolean addCourseToSchedule(String name, String section) {
		Course addCourse = getCourseFromCatalog(name, section);

		if (addCourse == null) {
			return false;
		}

		for (int i = 0; i < schedule.size(); i++) {
			Activity activity = schedule.get(i);
			if (addCourse.isDuplicate(activity)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		return schedule.add(addCourse);
	}

	/**
	 * Removes a course from schedule specified by name and section.
	 * 
	 * @param idx the index of the schedule being removed.
	 * 
	 * @return true if the course was removed, false if not.
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Resets the schedule by removing all scheduled courses.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<>();
	}

	/**
	 * Sets the title of the schedule.
	 * 
	 * @param title the title of the schedule.
	 * @throws IllegalArgumentException if title is null.
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Adds an event to the schedule.
	 * 
	 * @param eventTitle       the name of the event being added.
	 * @param eventMeetingDays the meeting days of the event being added.
	 * @param eventStartTime   the time the event begins.
	 * @param eventEndTime     the time the event ends.
	 * @param eventDetails     the details of the event.
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			String eventDetails) {
		Event e = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);

		for (int i = 0; i < schedule.size(); i++) {
			Activity activity = schedule.get(i);
			if (e.isDuplicate(activity)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
		}
		schedule.add(e);
	}
}
