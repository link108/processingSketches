package Tiles;

public interface TileFiller {
  public void setupTile(Tile tile);
  public void render(Tile tile);
  public int getFillColor();
  public int getBackgroundColor();
}
