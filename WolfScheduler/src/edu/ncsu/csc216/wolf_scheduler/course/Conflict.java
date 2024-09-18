/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Interface to check overlap/conflict between activities.
 * 
 * @author Anoushka Piduru
 */
public interface Conflict {

	/**
	 * Checks for a conflict with the new activity.
	 * 
	 * @param possibleConflictingActivity the activity being checked for conflicts.
	 * @throws ConflictException if a conflict rises.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}