package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Course class handles an Object Course and its name, title, section,
 * credits, instructor id, and meeting days/times.
 * 
 * @author Anoushka Piduru
 */
public class Course extends Activity {

	/** Course name's minimum length. */
	private static final int MIN_NAME_LENGTH = 5;
	/** Course name's maximum length. */
	private static final int MAX_NAME_LENGTH = 8;
	/** Course name's minimum abbreviation length. */
	private static final int MIN_LETTER_COUNT = 1;
	/** Course name's maximum abbreviation length. */
	private static final int MAX_LETTER_COUNT = 4;
	/** Course name's number of digits. */
	private static final int DIGIT_COUNT = 3;
	/** Course's section. */
	private static final int SECTION_LENGTH = 3;
	/** Course's maximum number of credits. */
	private static final int MAX_CREDITS = 5;
	/** Course's minimum number of credits. */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         name of Course.
	 * @param title        title of Course.
	 * @param section      section of Course.
	 * @param credits      credit hours for Course.
	 * @param instructorId instructor's unity id.
	 * @param meetingDays  meeting days for Course as series of chars.
	 * @param startTime    start time for Course.
	 * @param endTime      end time of Course.
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course.
	 * @param title        title of Course.
	 * @param section      section of Course.
	 * @param credits      credit hours for Course.
	 * @param instructorId instructor's unity id.
	 * @param meetingDays  meeting days for Course as a series of chars.
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name of the course.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set.
	 * @throws IllegalArgumentException if the name parameter is invalid.
	 */
	private void setName(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		int numLetters = 0;
		int numDigits = 0;
		boolean foundSpace = false;

		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);

			if (!foundSpace) {
				if (Character.isLetter(c)) {
					numLetters++;
				} else if (c == ' ') {
					foundSpace = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else {
				if (Character.isDigit(c)) {
					numDigits++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}

		if (numLetters < MIN_LETTER_COUNT || numLetters > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		if (numDigits != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section of the course.
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * 
	 * @param section the section to set.
	 * @throws IllegalArgumentException if section is invalid.
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		for (int i = 0; i < section.length(); i++) {
			char c = section.charAt(i);
			if (!Character.isDigit(c)) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}

	/**
	 * Returns the Course's credits.
	 * 
	 * @return the credits of the course.
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits.
	 * 
	 * @param credits the credits to set.
	 * @throws IllegalArgumentException if credits are invalid.
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor id.
	 * 
	 * @return the instructorId of the course.
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor id.
	 * 
	 * @param instructorId the instructorId to set.
	 * @throws IllegalArgumentException if instructor id is invalid.
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course.
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hash code for Course. Method is overridden to adapt functionality
	 * to Course parameters.
	 * 
	 * @return the hash code value for a Course.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Compares activity to another object for equality. Method is overridden to
	 * adapt functionality to Course objects.
	 * 
	 * @param obj the object to compare with.
	 * @return true if the objects are equal, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a string array of length 4 to represent course information. Method is
	 * overridden to adapt functionality to Course parameters.
	 * 
	 * @return String array containing short display information.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[4];

		shortDisplayArray[0] = name;
		shortDisplayArray[1] = section;
		shortDisplayArray[2] = getTitle();
		shortDisplayArray[3] = getMeetingString();

		return shortDisplayArray;
	}

	/**
	 * Returns a string array of length 7 to display the final schedule. Method is
	 * overridden to adapt functionality to Course parameters.
	 * 
	 * @return String array containing the long display information.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];

		longDisplayArray[0] = name;
		longDisplayArray[1] = section;
		longDisplayArray[2] = getTitle();
		longDisplayArray[3] = String.valueOf(credits);
		longDisplayArray[4] = instructorId;
		longDisplayArray[5] = getMeetingString();
		longDisplayArray[6] = "";

		return longDisplayArray;
	}

	/**
	 * Implements the Activity setMeetingDaysAndTime functionality. Method is
	 * overridden to adapt functionality to Course class.
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
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		} else {
			int monCount = 0;
			int tueCount = 0;
			int wedCount = 0;
			int thuCount = 0;
			int friCount = 0;
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
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			if (monCount > 1 || tueCount > 1 || wedCount > 1 || thuCount > 1 || friCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Determines if the course is a duplicate of an existing activity.
	 * 
	 * @param activity the object being compared with.
	 * @return true if activity is a duplicate, false if not.
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course newCourse = (Course) activity;
			return this.getTitle().equals(newCourse.getTitle());
		}
		return false;
	}
}
