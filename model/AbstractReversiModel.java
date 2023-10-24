package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbstractReversiModel /*implements GameModel*/ {
  private final CellColor[][] board;
  
  private CellColor turn;

  public AbstractReversiModel(int size) {
    board = new CellColor[size][];
    turn = CellColor.BLACK;
    int longest = size / 2;
    for (int i = 0; i < board.length; i++) {
      CellColor[] current = new CellColor[size];
      int start = 0;
      int end = current.length;
      int difference = longest - i;
      if (difference < 0) {
        end += difference;
      } else {
        start += difference;
      }
      while (start < end) {
        current[start] = CellColor.NONE;
        start++;
      }
      board[i] = current;
    }
  }

  public AbstractReversiModel() {
    board = new CellColor[11][];
    turn = CellColor.BLACK;
    int longest = 11 / 2;
    for (int i = 0; i < board.length; i++) {
      CellColor[] current = new CellColor[11];
      int start = 0;
      int end = current.length;
      int difference = longest - i;
      if (difference < 0) {
        end += difference;
      } else {
        start += difference;
      }
      while (start < end) {
        current[start] = CellColor.NONE;
        start++;
      }
      board[i] = current;
    }
    board[4][5] = CellColor.BLACK;
    board[4][6] = CellColor.WHITE;
    board[5][4] = CellColor.WHITE;
    board[5][6] = CellColor.BLACK;
    board[6][4] = CellColor.BLACK;
    board[6][5] = CellColor.WHITE;
  }
  
  private boolean isInBounds(int row, int col){
      return row >= 0 && col >= 0 && row < board.length && col < board[0].length;
  }

  public void print() {
    System.out.println(Arrays.deepToString(board));
  }

  /**
   * check if there is valid movement in the six directions.
   * @param row
   * @param col
   */
  public void makeMove(int row, int col) {
    if (!checkPass()) {
      turn = turn.getOpposite();
      return;
    }
    if (!isInBounds(row, col) || board[row][col] != CellColor.NONE) {
      throw new IllegalArgumentException("invalid coordinators");
    }
    for (Direction direction : Direction.values()) {
      List<Integer> adjacent = findAdjacentCells(Arrays.asList(row, col),direction);
        int flip = checkFlip(row, col, direction);
        if (flip > 0) {
          board[row][col] = turn;
        }
        int fixedRow = adjacent.get(0);
        int fixedCol = adjacent.get(1);
        for (int i = 0; i < flip; i ++) {
          board[fixedRow][fixedCol] = board[fixedRow][fixedCol].getOpposite();
        }
      }
    if (board[row][col] == CellColor.NONE) {
      throw new IllegalStateException("Invalid move");
    }
     turn = turn == CellColor.BLACK ?  CellColor.WHITE : CellColor.BLACK;
    }

    private int checkFlip(int r, int c, Direction direction) {
      List<Integer> adjacent = findAdjacentCells(Arrays.asList(r, c),direction);
      int adjacentRow = adjacent.get(0);
      int adjacentCol = adjacent.get(1);
      if (!isInBounds(adjacentRow, adjacentCol)) {
        return 0;
      }
      CellColor color = board[adjacentRow][adjacentCol];
      if (color == CellColor.NONE) {
        return 0;
      }
      if (color == null) {
        return 0;
      }
      if (color.getOpposite() != turn) {
        return 0;
      }
     return checkContinues(direction, adjacent, turn);
    }

  private int checkContinues(Direction direction, List<Integer> currentPosition, CellColor color) {
    int r = currentPosition.get(0);
    int c = currentPosition.get(1);
    CellColor current = board[r][c];
    int replace = 1;
    while(true) {
      r += direction.getRowOffset();
      c += direction.getColOffset();
      if (!isInBounds(r, c)) {
        return 0;
      }
      current =  board[r][c];
      if (current == color) {
        return replace;
      } else if (current == CellColor.NONE) {
        return 0;
      } else if (current.getOpposite() == color){
        replace++;
      }
    }
  }

  private boolean checkPass() {
    for (int i = 0; i < board.length; i ++) {
      for (int j = 0; j < board[i].length; j ++) {
        if (board[i][j] != CellColor.NONE) {
          continue;
        }
        for (Direction direction : Direction.values()) {
          int flip = checkFlip(i, j, direction);
          if (flip > 0) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private List<Integer> findAdjacentCells(List<Integer> current, Direction direction) {
    return Arrays.asList(current.get(0) + direction.getRowOffset(), current.get(1) + direction.getColOffset());
  }

  private List<List<Integer>> findAllAdjacentCells(List<Integer> current) {
    List<List<Integer>> adjacent = new ArrayList<>();
    for (Direction direction : Direction.values()) {
      adjacent.add(Arrays.asList(current.get(0) + direction.getRowOffset(), current.get(1) + direction.getColOffset()));
    }
    return adjacent;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < board.length; i ++) {
      int longest = 11 / 2;
      int blankLine = Math.abs(longest - i);
      builder.append(" ".repeat(blankLine));
      for (int j = 0; j < board[i].length; j ++) {
        CellColor color = board[i][j];
        if (color == null) {
          continue;
        } else {
          if (color == CellColor.NONE) {
            builder.append("_");
            builder.append(" ");
          } else if (color == CellColor.BLACK) {
            builder.append("X");
            builder.append(" ");
          } else {
            builder.append("O");
            builder.append(" ");
          }
        }
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    AbstractReversiModel model = new AbstractReversiModel();
    System.out.println(model);
    model.makeMove(4, 7);
    System.out.println(model);
    model.makeMove(6, 3);
    System.out.println(model);
  }
}
