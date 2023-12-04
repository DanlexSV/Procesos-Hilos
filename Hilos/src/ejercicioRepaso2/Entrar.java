package ejercicioRepaso2;

import java.util.Random;

public class Entrar extends Thread {
	Random r = new Random();

	Ratones rt;
	private int rataH = 0, rataM = 0, ratonesDentro = 0, maxRatones;

	public Entrar(Ratones rt) {
		this.rt = rt;
		maxRatones = 20;
		start();
	}

	@Override
	public void run() {
		while (ratonesDentro < maxRatones) {
			if (rt.entraHembra(rataH, rataM)) {
				rataH++;
				ratonesDentro++;
				System.out.println("Ha entrado una Hembra.");
				System.out.println("Hay " + ratonesDentro + " en la casa\n");
			} else if (rt.entraMacho(rataH, rataM)) {
				rataM++;
				ratonesDentro++;
				System.out.println("Ha entrado un Macho.");
				System.out.println("Hay " + ratonesDentro + " en la casa\n");
			}

			try {
				Thread.sleep((int) ((0.5 + r.nextDouble()) * 1000));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
