package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.NoSuchElementException;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Anoushka Piduru
 * @author Sarah Heckman
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Course> courses = new ArrayList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());

				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course current = courses.get(i);
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				// Don't do anything here
			}
		}

		fileReader.close();
		return courses;
	}

	/**
	 * Reads the list of Courses
	 * 
	 * @param line each line in the list of Courses
	 * @return a newly constructed Course object
	 * @throws IllegalArgumentException if too many tokens are input
	 */
	private static Course readCourse(String line) {
		Scanner scnr = new Scanner(line);
		scnr.useDelimiter(",");

		int startTime = 0;
		int endTime = 0;

		try {
			String name = scnr.next();
			String title = scnr.next();
			String section = scnr.next();
			int credits = scnr.nextInt();
			String instructorId = scnr.next();
			String meetingDays = scnr.next();

			if ("A".equals(meetingDays)) {
				if (scnr.hasNext()) {
					scnr.close();
					throw new IllegalArgumentException("Invalid token number");
				} else {
					scnr.close();
					return new Course(name, title, section, credits, instructorId, meetingDays);
				}
			} else {
				startTime = scnr.nextInt();
				endTime = scnr.nextInt();
			}
			if (scnr.hasNext()) {
				scnr.close();
				throw new IllegalArgumentException("Invalid token number");
			}

			scnr.close();
			return new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Invalid token number");
		}
	}

}