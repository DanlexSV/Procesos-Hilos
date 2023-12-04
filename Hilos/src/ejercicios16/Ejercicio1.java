package ejercicios16;	

public class Ejercicio1 {
	public static void main(String[] args) {

		boolean shouldStop = false;
		Thread segundosThread = new Thread(new Segundos(shouldStop));
		Thread minutosThread = new Thread(new Minutos(shouldStop));

		minutosThread.start();
		segundosThread.start();

		try {
			segundosThread.join();
			minutosThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Minutos implements Runnable {
	private boolean shouldStop;

	public Minutos(boolean shouldStop) {
		this.shouldStop = shouldStop;
	}

	@Override
	public void run() {
		for (int i = 0; i <= 2 && !shouldStop; i++) {
			System.out.println("Minutos: " + i);
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Segundos implements Runnable {
	private boolean shouldStop;

	public Segundos(boolean shouldStop) {
		this.shouldStop = shouldStop;
	}

	@Override
	public synchronized void run() {
		int j = 0;
		for (int i = 1; i <= 60 && !shouldStop; i++) {
			System.out.println("Segundos: " + i);
			if (i == 60) {
				i = 0;
				j++;
			}
			if (j == 2 && i == 30)
				shouldStop = true;

			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (shouldStop)
			System.out.println("Deteniendo los hilos...");
	}
}