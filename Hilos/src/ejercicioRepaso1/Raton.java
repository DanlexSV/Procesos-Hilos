package ejercicioRepaso1;

import java.util.Random;

public class Raton extends Thread {
	Random r = new Random();
	
	private int trozos;

	public Raton() {
		trozos = 50;
		start();
	}

	@Override
	public void run() {

		int numRatones = 5;

		while (true) {
			
			trozos--;
			System.out.println("El raton " + numRatones + " ha cogido un trozo.\nQuedan " + trozos + " trozos.\n");
			numRatones--;

			try {
				Thread.sleep((int) (r.nextInt(5) * 100)) ;
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if (numRatones == 0)
				numRatones = 5;
			if (trozos == 0)
				break;
		} 
	}

}
