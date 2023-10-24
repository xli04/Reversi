package model;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a direction relative to a given cell on the board. Any cell that is adjacent to
 *
 */
public enum Direction {
  LEfT(0, -1),
  RIGHT(0, 1),
  RIGHTUP(-1, 1),
  RIGHTDOWN(1, 0),
  LEFTUP(1, 0),
  LEFTDOWN(1, -1);

  private final int rowOffset;
  private final int colOffset;

  private Direction(int rowOffset, int colOffset) {
    this.rowOffset = rowOffset;
    this.colOffset = colOffset;
  }

  public int getRowOffset(){
    return this.rowOffset;
  }

  public int getColOffset(){
    return this.colOffset;
  }
}
