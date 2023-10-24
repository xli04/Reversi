package model;

/**
 * The game board and associated logic.
 */
public interface GameModel {
  /**
   * Checks whether a play can be made at the specified row and column,
   * where (0, 0) is the top left cell of the board.
   *
   * @param row   The 0-indexed row.
   * @param col   The 0-indexed column.
   * @param color The color we are trying to play at the specified cell.
   * @return true iff the move is legal.
   * @throws IllegalArgumentException if the position is out of bounds.
   * @throws IllegalStateException    if the game is over.
   */
  boolean canMove(int row, int col, CellColor color);

  /**
   * Plays a disc at the specified row and column if the move is legal,
   * and the specified color is either black or white.
   * A play is legal for player A if the disc being played is adjacent1 (in at least one direction)
   * to a straight line of the opponent player B’s discs, at the far end of which is another player
   * A disc.
   * {@link CellColor#BLACK} or {@link CellColor#WHITE}.
   *
   * @param row       The 0-indexed row.
   * @param col       The 0-indexed column.
   * @param cellColor The color we are trying to play at the specified cell.
   * @throws IllegalArgumentException if the position is not in bounds, or if
   *                                  the color is
   *                                  {@link CellColor#NONE}
   */
  void makeMove(int row, int col, CellColor cellColor);

  /**
   * Returns the score for the specified color.
   * //TODO: Finish
   *
   * @param cellColor the color
   * @return the score for the specified color
   */
  int getScore(CellColor cellColor);

  /**
   * Checks whether the game is over. There are three ways the game can end:
   * //TODO: Finish
   *
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Makes a fully independent "deep" copy of this model sharing none of its
   * data.
   * //TODO: Finish
   *
   * @return the deep copy of this model
   */
  GameModel deepCopy();

  /**
   * //TODO: Finish
   * Gets a string representation of this model showing the colors at each
   * board position. Specifically, each position is represented by a single
   * character, the first character of the {@link CellColor#getName()}.
   * These characters appear in the string in row-major order, with a
   * newline character after each row.
   * <p>
   * For example, a 2x2 board with a white cell in the upper-left corner
   * and a black cell in the upper-right corner, would be represented as
   * "WB\nnn".
   *
   * @return a string representation of the board position
   */
  default String getBoardString() {
    return null;
  }
}
