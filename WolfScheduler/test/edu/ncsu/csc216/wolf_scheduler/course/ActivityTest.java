/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Activity class.
 * 
 * @author Anoushka Piduru
 */
class ActivityTest {

	/**
	 * Test method for non conflicting activities
	 * {@link edu.ncsu.csc216.wolf_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)}.
	 */
	@Test
	void testCheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330,
				1445);
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));

	}

	/**
	 * Test checkConflict method with conflicting activities.
	 */
	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
	}

	/**
	 * Test checkConflict with activities of different meeting days, but same
	 * meeting times.
	 */
	@Test
	public void testSameTimeDifferentMeetingDays() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "002", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330,
				1445);
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}

	/**
	 * Test checkConflict with activities of same meeting days, but different
	 * meeting times.
	 */
	@Test
	public void testDifferentTimeSameMeetingDays() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "002", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1530,
				1645);
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}

	/**
	 * Test checkConflict with one activity having the same start time as another
	 * activity's end time.
	 */
	@Test
	public void testEndTimeEqualsStartTime() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "002", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1445,
				1530);
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());
		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
	}

	/**
	 * Test checkConflict with activities having the same meeting times on only one
	 * day.
	 */
	@Test
	public void testConflictOnSingleDay() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "002", 3, "sesmith5", "TH", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MH", 1330,
				1445);
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());
		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
	}
}
