package life.board
/**
 */

public class GroovyCellFactory implements CellFactory {

  int totalRows, totalColumns
  List<Cell> cells

  public GroovyCellFactory(GameBoard board) {
    totalColumns = board.totalColumns
    totalRows = board.totalRows
    cells = board.cells
  }

  public List<Cell> createGameBoardCells() {    
    createCells();
    makeCellsNeighbourAware();
    return cells;
  }

  private void createCells() {
    (1..totalRows).each {row ->
      (1..totalColumns).each {column ->
        cells.add(new Cell(row, column));
      }
    }
  }

  private void makeCellsNeighbourAware() {

    Cell[][] cellArray = new Cell[totalRows][totalColumns];

    cells.each {Cell cell ->
      cellArray[cell.row - 1][cell.getColumn() - 1] = cell;
    }

    cells.each {Cell cell ->
      (startIndex(cell.row)..endRow(cell.row)).each {int row ->
        (startIndex(cell.column)..endColumn(cell.column)).each {int column ->
          if (!(cell.isHere(row, column))) {
            addCellListener(cellArray, cell, row, column);
          }
        }
      }
    }
  }

  private int endColumn(int cellColumn) {
    return (totalColumns == cellColumn ? totalColumns : cellColumn + 1);
  }

  private int endRow(int cellRow) {
    return (totalRows == cellRow ? totalRows : cellRow + 1);
  }

  private int startIndex(int idx) {
    return (idx == 1 ? 1 : idx - 1);
  }

  private void addCellListener(Cell[][] cells, Cell cell, int row, int column) {
    cell.addCellListener(cells[row - 1][column - 1]);
  }

}