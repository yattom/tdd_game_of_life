/**
 * Created by yattom on 2017/07/01.
 */
public class Generation {
    private final GameOfLife gameOfLife;

    public Generation(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
    }

    public boolean isAlive(Coord coord) {
        return gameOfLife.getRule().willBeAlive(coord, gameOfLife.providePreviousGenerationOf(this));
    }

    public GameOfLife getGameOfLife() {
        return gameOfLife;
    }

    public int countAliveCells(Coord coord) {
        int aliveCount = 0;
        for (Coord neighbour : coord.getNeighbours()) {
            if (isAlive(neighbour)) {
                aliveCount += 1;
            }
        }
        return aliveCount;
    }
}
