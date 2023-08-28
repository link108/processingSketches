package Tiles.Bots;

import Colors.ColorPallete;
import Tiles.Tile;
import Tiles.TileFiller;
import Tiles.TilesPixel;
import processing.core.PApplet;

public class TileBot extends Tile {

    private Bot bot;

    public TileBot(PApplet sketch, int i, int j, int side, ColorPallete colorPallete, TileFiller tileFiller) {
        super(sketch, i, j, side, colorPallete, tileFiller);

        TilesPixel tilesPixel = getRandomPixelInBoundary();
        this.bot = new Bot(this, tilesPixel.i, tilesPixel.j);
    }


    public void initialDraw() {
        sketch.stroke(0);
        sketch.fill(backgroundColor);
        sketch.rect(x, y, side, side);
    }

    @Override
    public void render(){
        bot.step(this);
        this.debugPrint();
    }

}
