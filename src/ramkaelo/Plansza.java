package ramkaelo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.util.*;
import java.util.List;
import java.util.Timer;

import javax.swing.*;

public class Plansza extends JPanel {
	private Rectangle Pojazd;
	private List<Strzal> strzaly;
	private List<Wrog> wrogowie;
	private List<int[]> strzalyKier;
	private boolean trafionyPojazd;
	public boolean close;

	public Plansza(){
		Pojazd = new Rectangle(245,245,20,20);
		close = false;
		strzaly = new ArrayList<Strzal>();
		wrogowie = new ArrayList<Wrog>();
		strzalyKier = new ArrayList<int[]>();
		trafionyPojazd = false;
		int [] p = {50, 50};
		int [] p1 = {400, 450};
		int [] p2 = {350, 150};
		int [] p3 = {100, 400};
		wrogowie.add( new Wrog( p ) );
		wrogowie.add( new Wrog( p1 ) );
		wrogowie.add( new Wrog( p2 ) );
		wrogowie.add( new Wrog( p3  ) );
		p[0] = -1;
		p[1] = 0;
		strzalyKier.add( p );
		p1[0] = 0;
		p1[1] = 1;
		strzalyKier.add( p1 );
		p2[0] = 0;
		p2[1] = -1;
		strzalyKier.add( p2 );
		p3[0] = 1;
		p3[1] = 0;
		strzalyKier.add( p3 );
		setBackground(Color.black);
		setPreferredSize(new Dimension(500,500));
		addKeyListener(new MojKeyListener());
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				JLabel label1 = new JLabel("" + ((int)e.getX()));
				label1.setForeground(Color.WHITE);
				add(label1);
				if ( (int)e.getX() <= (int)Pojazd.getWidth() + (int)Pojazd.getX()   &&
						(int)e.getY() <= (int)Pojazd.getHeight() + (int)Pojazd.getY()){

				}
//				Pojazd.setBounds((int)e.getX(), (int)e.getY(), (int)Pojazd.getWidth(), (int)Pojazd.getHeight());
				repaint();
			}
		});

//	addMouseMotionListen	er(new MouseMotionAdapter(){
//		public void mouseDragged(MouseEvent e)
//		{
//			Pojazd.setBounds((int)e.getX(), (int)e.getY(), (int)Pojazd.getWidth(), (int)Pojazd.getHeight());
//			repaint();
//		}
//	});
		setFocusable(true);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run()
			{
				sunStrzaly();
			}
		};
		timer.schedule( task, 0L ,10L);
		TimerTask task1 = new TimerTask() {
			public void run()
			{
				wrodzyStrzelaja();
			}
		};
		timer.schedule( task1, 0L ,1500L);
	}

	public boolean toClose () {
		return close;
	}

	public void wrodzyStrzelaja() {
		for( Wrog wrog : wrogowie ) {
			if( !wrog.trafiony ) {
				for (int i = 0; i < 4; i++) {
					strzaly.add(new Strzal(wrog.strzalyPoz.get(i), strzalyKier.get(i), false));
				}
			}
		}
	}

	public void sunStrzaly() {
		for (int i = 0; i < strzaly.size(); i++) {
			Strzal s = strzaly.get(i);
			s.sun();
			int x = (int)s.punkt.getX()+1;
			int y = (int)s.punkt.getY()+1;
			int xW = 0;
			int yW = 0;
			int hW = 0;
			int wW = 0;
			if ( s.wlasciciel ){
				boolean won = true;
				for( Wrog wrog : wrogowie ) {
					if( !wrog.trafiony ){
						won = false;
						xW = (int) wrog.on.getX();
						yW = (int) wrog.on.getY();
						wW = (int) wrog.on.getWidth();
						hW = (int) wrog.on.getHeight();
						if ( x > xW && x < xW+wW && y > yW && y < yW+hW ) {
							wrog.trafiony = true;
							System.out.println("shooted");
						}
					}
				}
				if(won){
					JOptionPane pane = new JOptionPane();
					int result = pane.showConfirmDialog(this, "You won!!! Play again?");
					if ( result == JOptionPane.OK_OPTION ) {
						this.playAgain();
					}
				}
			}else if(!trafionyPojazd){
				xW = (int) Pojazd.getX();
				yW = (int) Pojazd.getY();
				wW = (int) Pojazd.getWidth();
				hW = (int) Pojazd.getHeight();
				if ( x > xW && x < xW+wW && y > yW && y < yW+hW ) {
					trafionyPojazd = true;
					System.out.println("shooted Pojazd");
					JOptionPane pane = new JOptionPane();
					int result = pane.showConfirmDialog(this, "You lose. Play again?");
					if ( result == JOptionPane.OK_OPTION ) {
						this.playAgain();
					}else {

					}

				}
			}
		}
		repaint();
	}

	public void playAgain() {
		System.out.println("ok");
		trafionyPojazd = false;
		Pojazd.setLocation(245, 245);
		for( Wrog wrog: wrogowie ) {
			wrog.trafiony = false;
		}
		strzaly.clear();
	}

	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D graphics2d = (Graphics2D) graphics;
		if( !trafionyPojazd ) {
			graphics2d.setColor(Color.yellow);
		} else {
			graphics2d.setColor(Color.gray);
		}
		graphics2d.fill(Pojazd);
		for ( int i = 0; i< strzaly.size(); i++ ){
			if ( strzaly.get(i).wlasciciel )
				graphics2d.fill(strzaly.get(i).punkt);
		}
		graphics2d.setColor(Color.red);
		for ( int i = 0; i< strzaly.size(); i++ ){
			if ( !strzaly.get(i).wlasciciel )
				graphics2d.fill(strzaly.get(i).punkt);
		}
		for( Wrog wrog : wrogowie ) {
			if( !wrog.trafiony ){
				graphics2d.fill(wrog.on);
			}
		}
		graphics2d.setColor(Color.gray);
		for( Wrog wrog : wrogowie ) {
			if( wrog.trafiony ){
				graphics2d.fill(wrog.on);
			}
		}
	}
	
	public void PrzesunPojazdX(int przesuniecie){
		Pojazd.setBounds((int)Pojazd.getX()+przesuniecie, (int)Pojazd.getY(), (int)Pojazd.getWidth(), (int)Pojazd.getHeight());
	}
	public void PrzesunPojazdY(int przesuniecie){
		Pojazd.setBounds((int)Pojazd.getX(), (int)Pojazd.getY()+przesuniecie, (int)Pojazd.getWidth(), (int)Pojazd.getHeight());
	}
	
	private class MojKeyListener implements KeyListener{
		public void keyPressed(KeyEvent keyEvent){
			if (!trafionyPojazd) {
				int[] poz = new int[2];
				double x = Pojazd.getX();
				double y = Pojazd.getY();
				double width = Pojazd.getWidth();
				double height = Pojazd.getHeight();
				int[] kier = new int[2];

				switch(keyEvent.getKeyCode()){
				case KeyEvent.VK_RIGHT : PrzesunPojazdX(10); break;
				case KeyEvent.VK_LEFT : PrzesunPojazdX(-10); break;
				case KeyEvent.VK_UP : PrzesunPojazdY(-10); break;
				case KeyEvent.VK_DOWN : PrzesunPojazdY(10); break;
				case KeyEvent.VK_ESCAPE : System.exit(0); break;
				case KeyEvent.VK_W :
					poz[0] = ((int) (x + (width / 2)));
					poz[1] = ((int) y);
					kier = new int[2];
					kier[0] = 0;
					kier[1] = -1;
					strzel( kier, poz);
					break;
				case KeyEvent.VK_S :
					poz[0] = ((int) (x + (width / 2)));
					poz[1] = ((int) (y + height) );
					kier = new int[2];
					kier[0] = 0;
					kier[1] = 1;
					strzel( kier, poz);
					break;
				case KeyEvent.VK_A :
					poz[0] = ((int) (x));
					poz[1] = ((int) (y + (height/2)));
					kier = new int[2];
					kier[0] = -1;
					kier[1] = 0;
					strzel( kier, poz);
					break;
				case KeyEvent.VK_D :
					poz[0] = ((int) (x + (width)));
					poz[1] = ((int) (y + (height / 2 )));
					kier = new int[2];
					kier[0] = 1;
					kier[1] = 0;
					strzel( kier, poz);
					break;
				}
				repaint();
			}
		}
		public void keyReleased(KeyEvent keyEvent){}
		public void keyTyped(KeyEvent keyEvent){}
	}

	private void strzel( int[] kier, int[] poz ) {

		Strzal s = new Strzal( poz, kier, true);
		System.out.print(poz[1]);
		strzaly.add(s);
	}

}
