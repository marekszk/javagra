package ramkaelo;

import javafx.event.EventType;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.soap.Node;
import java.util.Timer;
import java.util.TimerTask;

public class MojaRamka extends JFrame{
	public MojaRamka(){
		super("To jest napis na ramce");
		EventType closeEvent = new EventType<>("closeEvent");
		setSize(450,160);
		setLocation(50,50);
		Plansza panel = new Plansza();
		add(panel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run()
			{
				if( panel.toClose() ) {
					System.out.println("close");
				}
			}
		};
		timer.schedule( task, 10000L ,10L);
	}

}
