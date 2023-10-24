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
  
  private boolean isInBounds(int row, int col){
    //TODO: Implement
    return false;
  }

  public void print() {
    System.out.println(Arrays.deepToString(board));
  }

  public void makeMove(int row, int col) {
    CellColor position = board[row][col];
    if (position != CellColor.NONE) {
      throw new IllegalArgumentException("invalid coordinators");
    }
  }

//  public boolean validCell()

  private List<List<Integer>> finsAdjacentCells(List<Integer> current) {
    List<List<Integer>> adjacent = new ArrayList<>();
    for (Direction direction : Direction.values()) {
      adjacent.add(Arrays.asList(current.get(0) + direction.getRowOffset(), current.get(1) + direction.getColOffset()));
    }
    return adjacent;
  }

  public static void main(String[] args) {
    AbstractReversiModel model = new AbstractReversiModel(11);
    model.print();
  }
}
