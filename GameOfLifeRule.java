/**
 * Created by yattom on 2017/07/01.
 */
public class GameOfLifeRule implements Rule {
    @Override
    public boolean willBeAlive(Coord coord, Generation previous) {
        int aliveCells = previous.countAliveCells(coord);
        if(previous.isAlive(coord) && (aliveCells == 2 || aliveCells == 3)) {
            return true;
        }
        if(!previous.isAlive(coord) && aliveCells == 3) {
            return true;
        }
        return false;
    }
}
