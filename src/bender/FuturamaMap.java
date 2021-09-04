package bender;

import java.util.ArrayList;
import java.util.List;

class FuturamaMap {
    private final Tile[][] tiles;
    private final int rows;
    private final int columns;
    private Position startPosition;
    private List<Position> teleporterPositions = new ArrayList<>(2);
    private int brokenObstaclesCount = 0;

    public FuturamaMap(char[][] tilesAsChar) {
        rows = tilesAsChar.length;
        columns = tilesAsChar[0].length;
        tiles = new Tile[rows][columns];

        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                tiles[i][j] = Tile.valueOfChar(tilesAsChar[i][j]);
                
                if(tiles[i][j] == Tile.START) {
                	startPosition = new Position(j, i);
                }
                else if(tiles[i][j] == Tile.TELEPORTER) {
                	teleporterPositions.add(new Position(j, i));
                }
            }
        }
    }
	
	public void breakObstacleAt(Position position) {
		tiles[position.y][position.x] = Tile.EMPTY;
		brokenObstaclesCount++;
	}

    Tile getTileAt(Position position) {
        return tiles[position.y][position.x];
    }

    public Position getStartPosition() {
		return startPosition;
    }

    public Position getOtherTeleporterPosition(Position teleporterPosition) {
        if(teleporterPosition.equals(teleporterPositions.get(0))) {
            return teleporterPositions.get(1);
        }
        else {
            return teleporterPositions.get(0);
        }
    }

	public int getBrokenObstaclesCount() {
		return brokenObstaclesCount;
	}
}