package bender;

public class BenderHistoryRecord {
	final Position position;
	final boolean drunk;
	final boolean stunned;
	final Direction direction;
	final int brokenObstaclesCount;
	
	public BenderHistoryRecord(Bender bender, int brokenObstaclesCount) {
		this.position = bender.getPosition();
		this.drunk = bender.isDrunk();
		this.stunned = bender.isStunned();
		this.direction = bender.getDirection();
		this.brokenObstaclesCount = brokenObstaclesCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brokenObstaclesCount;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + (drunk ? 1231 : 1237);
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + (stunned ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BenderHistoryRecord other = (BenderHistoryRecord) obj;
		if (brokenObstaclesCount != other.brokenObstaclesCount)
			return false;
		if (direction != other.direction)
			return false;
		if (drunk != other.drunk)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (stunned != other.stunned)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BenderHistoryRecord [position=" + position + ", drunk=" + drunk + ", stunned=" + stunned
				+ ", direction=" + direction + ", brokenObstaclesCount=" + brokenObstaclesCount + "]";
	}
}
