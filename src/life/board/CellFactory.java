package life.board;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CellFactory {

    GameBoard board;
    List<Cell> cells;

    public CellFactory(GameBoard board) {
        this.board = board;
    }

    public List<Cell> createGameBoardCells() {
        cells = new ArrayList<Cell>();
        createCells();
        makeCellsNeighbourAware();
        return cells;
    }

    private void createCells() {
        for (int row = 1; row <= board.totalRows; row++) {
            for (int column = 1; column <= board.totalColumns; column++) {
                cells.add(new Cell(row, column));
            }
        }
    }

    private void makeCellsNeighbourAware() {
        Cell[][] cellArray = new Cell[board.totalRows][board.totalColumns];
        for (Cell cell : cells) {
            cellArray[cell.getRow() - 1][cell.getColumn() - 1] = cell;
        }

        for (Cell cell : cells) {
            for (int row = startIndex(cell.getRow()); row <= endRow(cell.getRow()); row++) {
                for (int column = startIndex(cell.getColumn()); column <= endColumn(cell.getColumn()); column++) {
                    if (!(cell.getRow() == row && cell.getColumn() == column)) {
                        addCellListener(cellArray, cell, row, column);
                    }
                }
            }
        }
    }

    private int endColumn(int cellColumn) {
        return (board.totalColumns == cellColumn ? board.totalColumns : cellColumn + 1);
    }

    private int endRow(int cellRow) {
        return (board.totalRows == cellRow ? board.totalRows : cellRow + 1);
    }

    private int startIndex(int idx) {
        return (idx == 1 ? 1 : idx - 1);
    }

    private void addCellListener(Cell[][] cells, Cell cell, int row, int column) {
        cell.addCellListener(cells[row - 1][column - 1]);
    }
}
