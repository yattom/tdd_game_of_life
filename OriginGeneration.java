/**
 * Created by yattom on 2017/07/01.
 */
public class OriginGeneration extends Generation {
    public OriginGeneration(GameOfLife gameOfLife) {
        super(gameOfLife);
    }

    public boolean isAlive(Coord coord) {
        return getGameOfLife().isAlive(coord);
    }
}
