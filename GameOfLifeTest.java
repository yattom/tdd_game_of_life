import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by yattom on 2017/06/30.
 */
public class GameOfLifeTest {
    @Test
    public void セルを生きにできる() {
        GameOfLife game = new GameOfLife();
        Coord coord = new FixedCoord();
        game.live(coord);
        assertTrue(game.provideGeneration(1).isAlive(coord));
    }

    @Test
    public void セルを死ににできる() {
        GameOfLife game = new GameOfLife();
        Coord coord = new FixedCoord();
        game.live(coord);
        game.die(coord);
        assertFalse(game.provideGeneration(1).isAlive(coord));
    }

    @Test
    public void 生きている隣接セルの数を取得できる_二次元格子の場合() {
        GameOfLife game = new GameOfLife();
        game.live(new Coord2DGrid(0, 0));
        assertEquals(1, game.provideGeneration(1).countAliveCells(new Coord2DGrid(1, 0)));
    }

    @Test
    public void 第1世代のセルの状態を取得できる() {
        GameOfLife game = new GameOfLife();
        Coord coord = new FixedCoord();
        game.live(coord);
        Generation current = game.provideGeneration(1);
        assertTrue(current.isAlive(coord));
    }

    @Test(expected=IllegalArgumentException.class)
    public void 世代は1以上() {
        GameOfLife game = new GameOfLife();
        game.provideGeneration(0);
    }

    @Test
    public void 第n世代のセルの状態を取得できる() {
        GameOfLife game = new GameOfLife();
        game.live(new SequentialCoord(0));
        game.addRule(new 生きのセルに隣接するセルが生きそれ以外は死になる());
        Generation fifth = game.provideGeneration(5);
        assertTrue(fifth.isAlive(new SequentialCoord(4)));
        assertFalse(fifth.isAlive(new SequentialCoord(0)));
    }

    private class 生きのセルに隣接するセルが生きそれ以外は死になる implements Rule {
        public boolean willBeAlive(Coord coord, Generation previous) {
            return coord.getNeighbours().stream().anyMatch(c -> previous.isAlive(c));
        }
    }


    @Test
    public void ライフゲームのルール_生存_隣接2つ() {
        GameOfLife game = new GameOfLife();
        game.addRule(new GameOfLifeRule());
        game.live(new Coord2DGrid(0, 0));
        game.live(new Coord2DGrid(1, 0));
        game.live(new Coord2DGrid(0, 1));
        Generation next = game.provideGeneration(2);
        assertTrue(next.isAlive(new Coord2DGrid(0, 0)));
    }

    @Test
    public void ライフゲームのルール_生存_隣接3つ() {
        GameOfLife game = new GameOfLife();
        game.addRule(new GameOfLifeRule());
        game.live(new Coord2DGrid(0, 0));
        game.live(new Coord2DGrid(1, 0));
        game.live(new Coord2DGrid(0, 1));
        game.live(new Coord2DGrid(1, 1));
        Generation next = game.provideGeneration(2);
        assertTrue(next.isAlive(new Coord2DGrid(0, 0)));
    }

    @Test
    public void ライフゲームのルール_誕生() {
        GameOfLife game = new GameOfLife();
        game.addRule(new GameOfLifeRule());
        game.live(new Coord2DGrid(1, 0));
        game.live(new Coord2DGrid(0, 1));
        game.live(new Coord2DGrid(1, 1));
        Generation next = game.provideGeneration(2);
        assertTrue(next.isAlive(new Coord2DGrid(0, 0)));
    }

    @Test
    public void ライフゲームのルール_過疎_隣接1つ() {
        GameOfLife game = new GameOfLife();
        game.addRule(new GameOfLifeRule());
        game.live(new Coord2DGrid(0, 0));
        game.live(new Coord2DGrid(0, 1));
        Generation next = game.provideGeneration(2);
        assertFalse(next.isAlive(new Coord2DGrid(0, 0)));
    }

    @Test
    public void ライフゲームのルール_過疎_隣接0() {
        GameOfLife game = new GameOfLife();
        game.addRule(new GameOfLifeRule());
        game.live(new Coord2DGrid(0, 0));
        Generation next = game.provideGeneration(2);
        assertFalse(next.isAlive(new Coord2DGrid(0, 0)));
    }

    @Test
    public void ライフゲームのルール_過密() {
        GameOfLife game = new GameOfLife();
        game.addRule(new GameOfLifeRule());
        game.live(new Coord2DGrid(1, 0));
        game.live(new Coord2DGrid(0, 1));
        game.live(new Coord2DGrid(1, 1));
        game.live(new Coord2DGrid(1, 0));
        game.live(new Coord2DGrid(-1, 0));
        Generation next = game.provideGeneration(2);
        assertFalse(next.isAlive(new Coord2DGrid(0, 0)));
    }

}
