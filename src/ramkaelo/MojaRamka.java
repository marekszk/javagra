package ramkaelo;

import javafx.event.EventType;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.soap.Node;
import java.util.Timer;
import java.util.TimerTask;

public class MojaRamka extends JFrame{

	public Plansza panel;

	public MojaRamka(){
		super("To jest napis na ramce");
		panel = new Plansza();
		setSize(450,160);
		setLocation(50,50);
		add(panel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public Plansza getPanel() {
		return panel;
	}
}
