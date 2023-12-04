package practicaHilosSincronizados;

public class Globos {

	int[] globos;
	int maxGlobos = 10, maxHinchandose = 3, globoHinchandose = 0, numGlobo = 1, volumenMax = 5;

	public Globos() {
		globos = new int[maxGlobos];
		for (int i = 0; i < maxGlobos; i++)
			globos[i] = 0;
	}
	
	public synchronized int darGlobo() {
		while (globoHinchandose == maxHinchandose && numGlobo != (maxGlobos + 1)) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if (numGlobo == (maxGlobos + 1))
			return -1;
		
		globos[numGlobo -1] = 1;
		System.out.println("Globo num " + numGlobo + " se ha entregado al " + Thread.currentThread().getName());
		globoHinchandose++;
		
		notifyAll();
		return numGlobo++;
	}
	
	public synchronized boolean pincharGlobo() {
		while (globoHinchandose == 0 && numGlobo != (maxGlobos + 1)) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if (numGlobo == (maxGlobos + 1))
			return true;
		
		for (int i = 0; i < maxGlobos; i++) {
			if (globos[i] > 0 && globos[i] <= volumenMax) {
				System.out.println("Globo " + (i +1) + " pinchado por " + Thread.currentThread().getName());
				globos[i] = volumenMax + 2;
				globoHinchandose--;
				notifyAll();
				break;
			}
		}
		
		return globoHinchandose != 0;
	}
	
	public synchronized boolean hincharGlobo(int num) {
		if (globos[num -1] <= volumenMax)
			globos[num -1]++;
		else
			return true;
		
		if (globos[num -1] == (volumenMax +1)) {
			globoHinchandose--;
			System.out.println("Globo " + num + " ha estallado.");
			notifyAll();
			return true;
		} else {
			System.out.println("Globo " + num + " con volumen " + globos[num -1]);
			return false;
		}
	}
}
