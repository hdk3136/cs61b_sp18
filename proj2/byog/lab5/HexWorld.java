package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static final int s = 3;
    private static final long SEED = 39785673;
    private static final Random RANDOM = new Random(SEED);

    private static int beginX(Position p, int row) {
        return p.x - Math.min(2 * s - 1 - row, row);
    }

    private static int endX(Position p, int row) {
        return p.x + Math.min(2 * s - 1 - row, row) + s;
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.FLOOR;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.WATER;
            default: return Tileset.PLAYER;
        }
    }
    public static void addHexagon(Position p, TETile[][] world) {
        if (s < 2) {
            throw new IllegalArgumentException("The size of hexagon must be at least 2");
        }
        TETile t = randomTile();
        for (int row = 0; row < 2 * s; row += 1) {
            for (int i = beginX(p, row); i < endX(p, row); i += 1) {
                world[i][row + p.y] = TETile.colorVariant(t,64, 64, 64, new Random());
            }
        }
    }

    private static TETile[][] worldInitialize() {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        return world;
    }

    public static void drawSingleHexagon() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        Position start = new Position(WIDTH / 2 - s / 2, 0);
        TETile[][] world = worldInitialize();

        addHexagon(start, world);
        ter.renderFrame(world);
    }


    private static void updatePositionCrossLine(Position p, int currentRow) {
        int secondTopRow = HEIGHT / s - 3;
        if (currentRow == 0 || currentRow == secondTopRow) {
            p.x -= (2 * s - 1);
        } else {
            p.x -= (5 * s);
        }
        p.y += s;
    }

    private static void drawOneLineHexagon(Position start, int Number, TETile[][] world) {
        for (int i = 0; i < Number - 1; i += 1) {
            addHexagon(start, world);
            start.x += 10;
        }
        addHexagon(start, world);
    }

    public static void drawManyHexagons() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        Position start = new Position(WIDTH / 2 - s / 2, 0);
        TETile[][] world = worldInitialize();

        int rowNumber = (HEIGHT - s) / s;
        for (int i = 0; i < rowNumber; i += 1) {
            if (i == 0 || i == rowNumber - 1) {
                drawOneLineHexagon(start, 1, world);
            } else if (i % 2 == 1) {
                drawOneLineHexagon(start, 2, world);
            } else {
                drawOneLineHexagon(start, 3, world);
            }
            updatePositionCrossLine(start, i);
        }

        ter.renderFrame(world);

    }


    public static void main(String[] args) {
        //drawSingleHexagon();
        drawManyHexagons();
    }

}
