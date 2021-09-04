package bender;

import java.util.HashMap;
import java.util.Map;

enum Tile {
    START('@'),
    SUICIDE_CABIN('$'),
    BREAKABLE_OBSTACLE('X'),
    UNBREAKABLE_OBSTACLE('#'),
    SOUTH('S'),
    EAST('E'),
    WEST('W'),
    NORTH('N'),
    CIRCUIT_INVERTER('I'),
    BEER('B'),
    TELEPORTER('T'),
    EMPTY(' ');

    private static final Map<Character, Tile> BY_CHARACTER = new HashMap<>();
  
    static {
        for (Tile e : values()) {
            BY_CHARACTER.put(e.character, e);
        }
    }

    public final char character;

    private Tile(char character) {
        this.character = character;
    }

    public static Tile valueOfChar(char character) {
        return BY_CHARACTER.get(character);
    }
}
