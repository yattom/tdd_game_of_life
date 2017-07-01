import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yattom on 2017/07/01.
 */
public class SequentialCoord implements Coord {
    private final int index;

    public SequentialCoord(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SequentialCoord that = (SequentialCoord) o;

        return index == that.index;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public Collection<Coord> getNeighbours() {
        Set<Coord> neighbours = new HashSet<>();
        neighbours.add(new SequentialCoord(index - 1));
        return neighbours;
    }
}
