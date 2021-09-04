package bender;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position computeNextPosition(Position position, Direction direction) {
        switch(direction) {
			case EAST:
                return new Position(position.x + 1, position.y);
			case NORTH:
				return new Position(position.x, position.y - 1);
			case SOUTH:
			    return new Position(position.x, position.y + 1);
			case WEST:
				return new Position(position.x - 1, position.y);
            default:
                return new Position(-1, -1);
        }
    }

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}