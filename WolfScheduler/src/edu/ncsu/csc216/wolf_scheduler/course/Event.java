package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Event class is an extension of Activity that allows handling of specific
 * events.
 * 
 * @author Anoushka Piduru
 */
public class Event extends Activity {

	/** The events added to the schedule. */
	private String eventDetails;

	/**
	 * Constructs the Event object.
	 * 
	 * @param title        the title of the course.
	 * @param meetingDays  the days the course meets.
	 * @param startTime    the time the course begins.
	 * @param endTime      the time the course ends.
	 * @param eventDetails the events added.
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * Returns a String array of Event information.
	 * 
	 * @return String array containing short display Event information.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[4];

		shortDisplayArray[0] = "";
		shortDisplayArray[1] = "";
		shortDisplayArray[2] = getTitle();
		shortDisplayArray[3] = getMeetingString();

		return shortDisplayArray;
	}

	/**
	 * Returns a String array of Event information to be displayed.
	 * 
	 * @return String array displaying long display information.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];

		longDisplayArray[0] = "";
		longDisplayArray[1] = "";
		longDisplayArray[2] = getTitle();
		longDisplayArray[3] = "";
		longDisplayArray[4] = "";
		longDisplayArray[5] = getMeetingString();
		longDisplayArray[6] = eventDetails;

		return longDisplayArray;
	}

	/**
	 * Returns a comma separated value String of all Event fields.
	 * 
	 * @return String representation of Event.
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + eventDetails;
	}

	/**
	 * Returns the details of the event.
	 * 
	 * @return the eventDetails.
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the event details.
	 * 
	 * @param eventDetails the eventDetails to set.
	 * @throws IllegalArgumentException if eventDetails is null.
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}

		this.eventDetails = eventDetails;
	}

	/**
	 * Implements the Activity setMeetingDaysAndTime functionality. Method is
	 * overridden to include Saturday and Sunday.
	 * 
	 * @param meetingDays the days the course meets.
	 * @param startTime   the time the course begins.
	 * @param endTime     the time the course ends.
	 * @throws IllegalArgumentException if meeting days and times are invalid.
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int monCount = 0;
		int tueCount = 0;
		int wedCount = 0;
		int thuCount = 0;
		int friCount = 0;
		int satCount = 0;
		int sunCount = 0;
		for (int i = 0; i < meetingDays.length(); i++) {
			char c = meetingDays.charAt(i);
			if (c == 'M') {
				monCount++;
			} else if (c == 'T') {
				tueCount++;
			} else if (c == 'W') {
				wedCount++;
			} else if (c == 'H') {
				thuCount++;
			} else if (c == 'F') {
				friCount++;
			} else if (c == 'S') {
				satCount++;
			} else if (c == 'U') {
				sunCount++;
			} else {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		if (monCount > 1 || tueCount > 1 || wedCount > 1 || thuCount > 1 || friCount > 1 || satCount > 1
				|| sunCount > 1) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Determines if a duplicate Event was attempted to be added.
	 * 
	 * @param activity the object being compared with.
	 * @return true if event is a duplicate, false if not.
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event newEvent = (Event) activity;
			return this.getTitle().equals(newEvent.getTitle());
		}
		return false;
	}
}
