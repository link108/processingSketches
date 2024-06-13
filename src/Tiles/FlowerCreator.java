package Tiles;

import Colors.Color;
import Colors.ColorPallete;
import processing.core.PApplet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FlowerCreator implements TileFiller {
    private char[][] flowerOutline;
    private int outlineEdgeLength;
    private ColorPallete colorPallete;
    private Random random = new Random();
    private Boolean initialSetupComplete = false;

    private Map<String, Color> charColorMapping = new HashMap() {{
        put('0', new Color(0, 0, 0));

    }};

    public FlowerCreator(ColorPallete colorPallete) {
        this.flowerOutline = createFlowerOutline();
        this.outlineEdgeLength = this.flowerOutline.length;
        this.colorPallete = colorPallete;
    }

    public int getFillColor() {
        return colorPallete.getPallete().get(4).toColor();
    }

    public int getBackgroundColor() {
        return colorPallete.getPallete().get(1).toColor();
    }


    public char[][] createFlowerOutline() {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get("src/Tiles/flower-outline"));

            // Assuming the file is well-formed, create the 2D array
            int rows = lines.size();
            int cols = lines.get(0).length();
            char[][] array = new char[rows][cols];

            // Fill the array with characters from the file
            for (int i = 0; i < rows; i++) {
                array[i] = lines.get(i).toCharArray();
            }

            return array;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setupTile(Tile tile) {
        System.out.println("Colors in palletes:");
        for(Color color : colorPallete.getPallete()) {
            System.out.println(color.toColor());
        }

        if(!initialSetupComplete) {
            tile.sketch.stroke(0);
            tile.sketch.fill(getBackgroundColor());
            tile.sketch.rect(tile.x, tile.y, tile.side, tile.side);
            initialSetupComplete = true;
            System.out.println("Initial setup complete");
        }

        int buffer = outlineEdgeLength / 2;
        int max = tile.pixelsPerSide - buffer;
        int x = random.nextInt((max - buffer) + 1) + buffer;
        int y = random.nextInt((max - buffer) + 1) + buffer;
        renderFlower(tile, x, y);
    }

    public void render(Tile tile) {
        for(int i=0; i< tile.pixelsPerSide; i++) {
            for(int j=0; j < tile.pixelsPerSide; j++) {
                System.out.println("row: " + i + ", col: " + j + ", color: " + tile.getPixel(i, j).color);
                tile.getPixel(i, j).render();
                tile.getPixel(i, j).renderPixelBorder();
            }
        }
    }

    public void renderFlower(Tile tile, int center_x, int center_y) {
        int starting_x = center_x - (outlineEdgeLength / 2);
        int x = starting_x;
        int y = center_y - (outlineEdgeLength / 2);
        int color;
        for (char cs[] : this.flowerOutline) {
            for (char c : cs){
                TilesPixel tilesPixel = tile.getPixel(y, x);
                tilesPixel.setFilled();
                if(charColorMapping.keySet().contains(c)) {
                    color = charColorMapping.get(c).toColor();
                    System.out.println("Setting row: " + y + ", col: " + x + ", color (Black): " + color);
                } else {
                    color = colorPallete.getPallete().get(1).toColor();
                }
                tilesPixel.setColor(color);
                x += 1;
            }
            x = starting_x;
            y += 1;
        }
    }
}

