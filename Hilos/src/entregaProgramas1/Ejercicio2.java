package entregaProgramas1;

import javax.swing.*;

public class Ejercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		soltarPerros soltar = new soltarPerros();
		Thread personasT = new Thread(new Personas());
		Thread perrosT = new Thread(new Perros(soltar));
		Thread soltarP = new Thread(soltar);

		perrosT.start();
		personasT.start();
		soltarP.start();

		try {
			personasT.join();
			perrosT.join();
			soltarP.join();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

class Personas implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			System.out.println("Personas andando...");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

class Perros implements Runnable {

	private soltarPerros soltarPerros;

	public Perros(soltarPerros soltarPerros) {
		this.soltarPerros = soltarPerros;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!soltarPerros.isSoltarPerros()) {
			System.out.println("Perros sueltos...");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

class soltarPerros implements Runnable {
	
	private volatile boolean soltarPerros;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!soltarPerros) {
			String opt = JOptionPane.showInputDialog("Quieres soltar a los perros?\n Y/N.").toUpperCase();
			if ("Y".equals(opt))
				soltarPerros = true;
			try {
				Thread.sleep(2600);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (soltarPerros)
				System.out.println("Se han soltado todos los perros.");
		}
	}
	public boolean isSoltarPerros() {
		return soltarPerros;
	}
}