package ramkaelo;

import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;

/**
 * Created by s426259 on 10.06.2017.
 */
public class Strzal {

    private int[] kier;         // values - 0, 1, -1; id = 0 - x; = 1 - y;
    public boolean wlasciciel; // true - user; false - wróg;
    public Rectangle punkt;

    public Strzal ( int[] poz, int[] kier, boolean wlasciciel){
        this.kier = kier;
        this.wlasciciel = wlasciciel;
        this.punkt = new Rectangle(poz[0], poz[1],2,2);//new Point(poz[0], poz[1]);
    }

    public void sun () {
        this.punkt.setLocation( (int)this.punkt.getX()+this.kier[0], (int)this.punkt.getY()+this.kier[1]);
//        return this.punkt;
    }

    private boolean kolizja () {
        return false;
    }
}
