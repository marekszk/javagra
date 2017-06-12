package ramkaelo;

import javax.swing.*;
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

public class mainProgram {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
				{@Override
				public void run()
					{

						MojaRamka ramka = new MojaRamka();

						Timer timer = new Timer();
						TimerTask task = new TimerTask() {
							public void run()
							{
								if( ramka.getPanel().toClose() ) {
									ramka.dispose();
								}
							}
						};
						timer.schedule( task, 10000L ,10L);
					}
				});

	}

}
