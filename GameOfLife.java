import java.util.*;

/**
 * Created by yattom on 2017/06/30.
 */
public class GameOfLife {
    private Set<Coord> cells;
    private Rule rule;
    private List<Generation> generations;

    public GameOfLife() {
        cells = new HashSet<>();
        generations = new ArrayList<>();
    }

    public void live(Coord coord) {
        cells.add(coord);
    }

    public boolean isAlive(Coord coord) {
        return cells.contains(coord);
    }

    public void die(Coord coord) {
        cells.remove(coord);
    }

    public void addRule(Rule rule) {
        this.rule = rule;
    }

    public Generation provideGeneration(int nth) {
        if(nth < 1) {
            throw new IllegalArgumentException("nth muse be > 0");
        }
        generations.add(new OriginGeneration(this));
        int n = 2;
        while(generations.size() < nth) {
            generations.add(new Generation(this));
            n += 1;
        }
        return generations.get(nth - 1);
    }

    public Rule getRule() {
        return rule;
    }

    public Generation providePreviousGenerationOf(Generation generation) {
        int index = generations.indexOf(generation);
        if(index < 0) {
            throw new IllegalArgumentException("generation is not part of the hitsoty");
        }
        return generations.get(index - 1);
    }
}
