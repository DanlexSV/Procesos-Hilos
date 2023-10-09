package entregaProgramas1;

import javax.swing.*;

public class Ejercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int perros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de perros en valor numerico."));
		Thread personasT = new Thread(new Personas());
		Thread perrosT = new Thread(new Perros(perros));

		perrosT.start();
		personasT.start();

		try {
			personasT.join();
			perrosT.join(1000);
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
		for (int i = 0; i <= 5; i++) {
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

	private int perros;

	public Perros(int perros) {
		this.perros = perros;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (perros != 0) {
			System.out.println("Perros sueltos...");
			perros--;
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}