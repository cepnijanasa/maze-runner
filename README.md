# maze-runner
Maze solving application.

Implements API (A):
  * Input:
      - Maze: as two dimentional array.
      - Algorithm: name of algorithm that will be used.
  * Returns:
      - Fastest navigated path.
      - Name of algorithm used to find the fastest path.

Implements API (B):
  * Input:
    - Maze: as two dimentional array.
  * Returns:
    - Fastest navigated path.
    - Time needed to find the optimal path using all algorithms.

## Prerequisites
* maven 3.6.0+
* java 11+

## build
mvn clean package

## run
java -jar target\maze-runner-1.0-SNAPSHOT.jar
