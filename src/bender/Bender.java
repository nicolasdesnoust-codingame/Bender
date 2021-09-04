package bender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class Bender {
	private static final List<Direction> normalPriorities = Arrays.asList(
			Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST);
	private static final List<Direction> invertedPriorities = Arrays.asList(
			Direction.WEST, Direction.NORTH, Direction.EAST, Direction.SOUTH);
	
	private final FuturamaMap futuramaMap;
	private final Set<BenderHistoryRecord> history;

	private boolean drunk;
	private boolean stunned;
	private Position position;
	private List<Direction> priorities;
	
	public Bender(FuturamaMap futuramaMap) {
		this.futuramaMap = futuramaMap;
		this.history = new LinkedHashSet<>();
		this.drunk = false;
		this.stunned = false;
		this.priorities = new ArrayList<>(normalPriorities);
		this.position = futuramaMap.getStartPosition();
	}
	
	public boolean findSuicideCabin() {		
		boolean looping = false;

		while (futuramaMap.getTileAt(position) != Tile.SUICIDE_CABIN && !looping) {
			applyTileEffect(futuramaMap.getTileAt(position));
			moveToNextTile();
			
			looping = trackHistory();
		}

		return futuramaMap.getTileAt(position) == Tile.SUICIDE_CABIN;
	}
	
	private void applyTileEffect(Tile tile) {
		switch (tile) {
		case TELEPORTER:
	        position = futuramaMap.getOtherTeleporterPosition(position);
			break;
		case BEER:
			drunk = !drunk;
			break;
		case CIRCUIT_INVERTER:
			stunned = !stunned;
			setDirection(priorities.get(0));
			break;
		case EAST:
			setDirection(Direction.EAST);
			break;
		case NORTH:
			setDirection(Direction.NORTH);
			break;
		case SOUTH:
			setDirection(Direction.SOUTH);
			break;
		case WEST:
			setDirection(Direction.WEST);
			break;
		default:
			break;
		}
	}
	
	private void moveToNextTile() {
        Queue<Direction> directionsToMoveTo = new LinkedList<>(priorities);
        Position nextPosition = null;
        boolean nextPositionFound = false;
        Direction currentDirection = null;
        
        while(!nextPositionFound && !directionsToMoveTo.isEmpty()) {
        	currentDirection = directionsToMoveTo.poll();
            nextPosition = Position.computeNextPosition(position, currentDirection);
            
            Tile nextTile = futuramaMap.getTileAt(nextPosition);
            if(nextTile != Tile.BREAKABLE_OBSTACLE && nextTile != Tile.UNBREAKABLE_OBSTACLE) {
                nextPositionFound = true;
            }
            else if(nextTile == Tile.BREAKABLE_OBSTACLE && isDrunk()) {
            	futuramaMap.breakObstacleAt(nextPosition);
                nextPositionFound = true;
            }
        }
        
        setDirection(currentDirection);
        position = nextPosition;
    }

	private boolean trackHistory() {
		int currentBrokenObstaclesCount = futuramaMap.getBrokenObstaclesCount();
		BenderHistoryRecord historyRecord = new BenderHistoryRecord(this, currentBrokenObstaclesCount);
		return !history.add(historyRecord);
	}
	
	private void setDirection(Direction direction) {
		priorities = new LinkedList<>(
				isStunned() ? Bender.invertedPriorities
							: Bender.normalPriorities
		);
		priorities.remove(direction);
		priorities.add(0, direction);
	}
	
	public List<Direction> getPath() {
		return history.stream()
				.map(record -> record.direction)
				.collect(Collectors.toList());
	}
	
	public Position getPosition() {
		return position;
	}

	public Direction getDirection() {
		return priorities.get(0);
	}

	public boolean isDrunk() {
		return drunk;
	}

	public boolean isStunned() {
		return stunned;
	}
}
