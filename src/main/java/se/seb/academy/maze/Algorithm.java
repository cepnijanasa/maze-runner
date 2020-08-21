package se.seb.academy.maze;

public enum Algorithm {
	ALWAYS_LEFT(){
		@Override
		public IMazeRunner newMazeRunner() {return new AlwaysLeftRunner();}
	},
	ALWAYS_RIGHT() {
		@Override
		public IMazeRunner newMazeRunner() {return new AlwaysRightRunner();}
	},
	RANDOM() {
		@Override
		public IMazeRunner newMazeRunner() {return new RandomRunner();}
	};

	public static Algorithm getDefault() {
		return ALWAYS_RIGHT;
	}

	public abstract IMazeRunner newMazeRunner();
}
