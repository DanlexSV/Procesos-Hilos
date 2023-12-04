package ejercicioRepaso3;

import java.util.Random;

public class Asesinato extends Thread {
	Random r = new Random();

	Ratones rt;

	public Asesinato(Ratones rt) {
		super();
		this.rt = rt;
	}

	@Override
	public void run() {
		while (rt.getRatonesDentro() <= rt.getMaxRatones()) {
			try {
				Thread.sleep((int) ((2 + (r.nextDouble() * (3 - 2))) * 1000));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			synchronized (rt) {
				rt.hacerAsesinato();
				rt.notify();
			}
		}
	}

}