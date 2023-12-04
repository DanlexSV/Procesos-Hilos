package ejercicioRepaso3;

public class Ratones {
	
	protected volatile int ratonH, ratonM, ratonesDentro, maxRatones;

	public Ratones() {
		super();
		ratonH = 0;
		ratonM = 0;
		ratonesDentro = 0;
		maxRatones = 20;
		// TODO Auto-generated constructor stub
	}

	public int getRatonH() {
		return ratonH;
	}

	public void setRatonH(int ratonH) {
		this.ratonH = ratonH;
	}

	public int getRatonM() {
		return ratonM;
	}

	public void setRatonM(int ratonM) {
		this.ratonM = ratonM;
	}

	public int getMaxRatones() {
		return maxRatones;
	}

	public void setMaxRatones(int maxRatones) {
		this.maxRatones = maxRatones;
	}
	
	public int getRatonesDentro() {
		return ratonesDentro;
	}

	public void setRatonesDentro(int ratonesDentro) {
		this.ratonesDentro = ratonesDentro;
	}

	public synchronized boolean entraMacho() {
		if ((ratonH - ratonM) == 2) {
			ratonM++;
			System.out.println("Ha entrado un Macho");
			actualizarRatones();
			System.out.println("Hay " + ratonesDentro + " dentro de casa\n");
			return true;
		}
		return false;
	}
	
	public synchronized boolean entraHembra() {
		if (!entraMacho()) {
			ratonH++;
			System.out.println("Ha entrado una Hembra");
			System.out.println("Hay " + ratonesDentro + " dentro de casa\n");
			return true;
		}
		return false;
	}
	
	public synchronized void hacerAsesinato() {
			System.out.println("Se ha matado una Hembra");
			ratonH--;
			ratonesDentro--;
			System.out.println("Hay " + ratonesDentro + " dentro de casa\n");
	}
	
	public synchronized void actualizarRatones() {
		ratonesDentro = ratonH + ratonM;
	}
	
}
