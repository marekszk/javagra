package ramkaelo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by s426259 on 11.06.2017.
 */
public class Wrog {

    public List<int[]> strzalyPoz;
    public Rectangle on;
    public boolean trafiony;

    public Wrog ( int[] poz ) {
        this.on = new Rectangle( poz[0], poz[1], 10, 10);
        this.trafiony = false;
        strzalyPoz = new ArrayList<int[]>();
        int [] p = {poz[0], (poz[1]+5)};
        int [] p1 = {poz[0], (poz[1]+5)};
        int [] p2 = {poz[0], (poz[1]+5)};
        int [] p3 = {poz[0], (poz[1]+5)};
        strzalyPoz.add( p );
        p1[0] = poz[0]+5;
        p1[1] = poz[1]+10;
        strzalyPoz.add( p1 );
        p2[0] = poz[0]+5;
        p2[1] = poz[1];
        strzalyPoz.add( p2 );
        p3[0] = poz[0]+10;
        p3[1] = poz[1]+5;
        strzalyPoz.add( p3 );
    }
}
