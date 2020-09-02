package se.seb.academy.maze;

import java.time.Duration;
import java.util.List;

public class MazeRunnerResult {

	private List<Position> positionList;
	private Duration duration;

	public MazeRunnerResult(List<Position> positionList, Duration duration) {
		this.positionList = positionList;
		this.duration = duration;
	}

	public List<Position> getPositionList() {
		return positionList;
	}

	public Duration getDuration() {
		return duration;
	}
}
