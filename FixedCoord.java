import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by yattom on 2017/06/30.
 */
public class FixedCoord implements Coord {
    @Override
    public Collection<Coord> getNeighbours() {
        return new ArrayList<>();
    }
}
