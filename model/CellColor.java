package model;

/**
 * A representation of the colors of board positions and tiles in Islands of
 * Hex.
 */
public enum CellColor {
  /**
   * An NONE color, represent the empty position
   */
  NONE, // color not used

  /**
   * A white tile or a position with a white tile
   */
  WHITE,

  /**
   * A black tile or a position with a black tile
   */
  BLACK;

  /**
   * Returns the opposite of this color, where {@link #BLACK} and
   * {@link #WHITE} are opposites.
   *
   * @return the opposite of this color
   * @throws IllegalArgumentException if this is {@link #NONE}
   */
  public CellColor getOpposite() {
    switch (this) {
      case WHITE:
        return BLACK;
      case BLACK:
        return WHITE;
      case NONE:
        throw new IllegalArgumentException("Cannot toggle EMPTY");
      default:
        throw new IllegalArgumentException("Cannot toggle color %s, it is an unknown color");
    }
  }
}
