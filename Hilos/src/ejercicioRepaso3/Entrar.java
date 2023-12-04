package ejercicioRepaso3;

import java.util.Random;

public class Entrar extends Thread {
	Random r = new Random();

	Ratones rt;

	public Entrar(Ratones rt) {
		super();
		this.rt = rt;
	}

	@Override
	public void run() {		
		while (true) {
			try {
				Thread.sleep((int) ((0.5 + r.nextDouble()) * 1000));
			} catch (Exception e) {
				// TODO: handle exception
			}

			synchronized (rt) {
				do {
					if (rt.entraHembra())
						rt.actualizarRatones();
					else if (rt.entraMacho())
						rt.actualizarRatones();
					
					try {
						rt.wait();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				} while (rt.getRatonesDentro() <= rt.getMaxRatones());
			}
		}
	}

}
