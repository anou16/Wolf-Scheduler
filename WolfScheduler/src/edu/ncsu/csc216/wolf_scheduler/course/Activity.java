package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The abstract class, Activity, allows events to be added to the WolfScheduler.
 * Displays arrays for course catalogs and student schedules.
 * 
 * @author Anoushka Piduru
 */
public abstract class Activity {

	/** Course's max ending time (hours). */
	private static final int UPPER_HOUR = 24;
	/** Course's max ending time (minutes). */
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Populates rows of course catalog and student schedule.
	 * 
	 * @return String[] an array of length 4 containing course info.
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Displays the final schedule.
	 * 
	 * @return String[] an array of length 7 containing course info.
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * The constructor for Activity.
	 * 
	 * @param title       the title of the course.
	 * @param meetingDays the days the course meets.
	 * @param startTime   the time the course begins.
	 * @param endTime     the time the course ends.
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Course's title.
	 * 
	 * @return the title of the course.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title.
	 * 
	 * @param title the title to set.
	 * @throws IllegalArgumentException if title is invalid.
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Sets the meeting days and times for the course. If the course is arranged,
	 * "A", the times are not displayed. Verifies the meeting days and times.
	 * 
	 * @param meetingDays the days the course will meet.
	 * @param startTime   the time the course begins.
	 * @param endTime     the time the course ends.
	 * @throws IllegalArgumentException if any of the times or dates are invalid.
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;

		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns the Course's meeting days.
	 * 
	 * @return String meetingDays the days the course is held.
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time.
	 * 
	 * @return the startTime, the time the course begins.
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * 
	 * @return the endTime, the time the course ends.
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Returns the meeting days and times in string format.
	 * 
	 * @return String combining the course's meeting days, start times, and end
	 *         times.
	 */
	public String getMeetingString() {
		String startTimeStr = getTimeString(startTime);
		String endTimeStr = getTimeString(endTime);
		if ("A".equals(meetingDays)) {
			return "Arranged";
		}
		return meetingDays + " " + startTimeStr + "-" + endTimeStr;
	}

	/**
	 * Converts into military time and appends an AM or PM based on time.
	 * 
	 * @param time the military time.
	 * @return a string representing the time in 12-hour time, with an appended AM
	 *         or PM.
	 */
	private String getTimeString(int time) {
		String day = "";
		if (time < 1200) {
			day = "AM";
		} else {
			day = "PM";
		}
		int hours = time / 100;
		int mins = time % 100;

		if (hours > 12) {
			hours -= 12;
		}
		if (hours == 0) {
			hours = 12;
		}

		String min = "" + mins;
		if (mins < 10) {
			min = "0" + mins;
		}

		return hours + ":" + min + day;
	}

	/**
	 * Generates the hash code for an activity. Method is overridden to adapt
	 * functionality to Activity class.
	 * 
	 * @return the hash code for a particular activity.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares activity to another object for equality (same title, meetingDays,
	 * startTime, and endTime). Method is overridden to adapt functionality to
	 * Activity objects.
	 * 
	 * @param obj the object to compare with.
	 * @return true if the objects are equal, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Checks if an activity is a duplicate of another activity.
	 * 
	 * @param activity the activity to compare with.
	 * @return true if activity is a duplicate, false if not.
	 */
	public abstract boolean isDuplicate(Activity activity);
}