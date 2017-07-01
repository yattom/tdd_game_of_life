import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by yattom on 2017/06/30.
 */
public class Coord2DGrid implements Coord {
    private final int col;
    private final int row;

    private static int[][] NEIGHBOURS = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord2DGrid that = (Coord2DGrid) o;

        if (col != that.col) return false;
        return row == that.row;
    }

    @Override
    public int hashCode() {
        int result = col;
        result = 31 * result + row;
        return result;
    }

    public Coord2DGrid(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public Collection<Coord> getNeighbours() {
        Collection<Coord> neighbourCoords = new ArrayList<>();
        for(int[] neighbour: NEIGHBOURS) {
            neighbourCoords.add(new Coord2DGrid(row + neighbour[0], col + neighbour[1]));
        }

        return neighbourCoords;
    }
}
