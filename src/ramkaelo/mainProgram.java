package ramkaelo;

import java.awt.EventQueue;

public class mainProgram {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
				{@Override
				public void run()
					{
						new MojaRamka();
						}
				});

	}

}
