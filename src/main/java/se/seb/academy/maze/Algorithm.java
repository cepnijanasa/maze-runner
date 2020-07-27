package se.seb.academy.maze;

public enum Algorithm {
	ALWAYS_LEFT,
	ALWAYS_RIGHT;

	public static Algorithm getDefault() {
		return ALWAYS_RIGHT;
	}
}
