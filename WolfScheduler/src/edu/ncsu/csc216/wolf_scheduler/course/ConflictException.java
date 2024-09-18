/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * An exception is thrown when a conflict rises between activities.
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for ConflictException.
	 * 
	 * @param message the message specified for the Exception object.
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Constructor for ConflictException with an author specified default message.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
