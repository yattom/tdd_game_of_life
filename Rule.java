/**
 * Created by yattom on 2017/07/01.
 */
public interface Rule {
    boolean willBeAlive(Coord coord, Generation previous);
}
