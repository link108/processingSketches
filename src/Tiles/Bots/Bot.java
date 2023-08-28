package Tiles.Bots;

import Tiles.Tile;
import Tiles.TilesPixel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Bot {

    public int x;
    public int y;
    public Tile tile;
    public List<TilesPixel> filledPixels;

    public int steps = 10;

    public List<Integer> directions = Arrays.asList(-1, 0, 1);

    public Bot(Tile tile, int starting_x, int starting_y) {
        this.x = starting_x;
        this.y = starting_y;
        this.tile = tile;
        this.filledPixels = new ArrayList<>();
    }

    public int getRandomDirection() {
        Random random = new Random();
        return directions.get(random.nextInt(directions.size()));
    }

    // if updated is out of bounds, return starting position
    public int getNextInBounds(int start) {
        int next = start + getRandomDirection();
        return tile.isInBounds(next) ? next : start;
    }

    public void step(Tile tile) {
        for (int i=0; i < steps; i++) {
            this.x = getNextInBounds(x);
            this.y = getNextInBounds(y);
            TilesPixel tilesPixel = tile.getPixel(x,y);
            filledPixels.add(tilesPixel);
            tilesPixel.setFilled();
        }
        // do this as a separate loops so the tilepixel's printBorder function knows about the filled pixels
        for (TilesPixel tp : filledPixels) {
            tp.render();
        }
    }



}
