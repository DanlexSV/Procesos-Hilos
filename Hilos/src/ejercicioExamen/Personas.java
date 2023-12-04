package ejercicioExamen;

public class Personas {

	protected int numChicos, numChicas, maxPersonas;

	public Personas() {
		super();
		// TODO Auto-generated constructor stub
		numChicos = 0;
		numChicas = 0;
		maxPersonas = 30;
	}

	public int getNumChicos() {
		return numChicos;
	}

	public void setNumChicos(int numChicos) {
		this.numChicos = numChicos;
	}

	public int getNumChicas() {
		return numChicas;
	}

	public void setNumChicas(int numChicas) {
		this.numChicas = numChicas;
	}

	public int getMaxPersonas() {
		return maxPersonas;
	}

	public void setMaxPersonas(int maxPersonas) {
		this.maxPersonas = maxPersonas;
	}

	public synchronized int personasDentro() {
		return (numChicos + numChicas);
	}
	
	public synchronized void Aforo() {
		System.out.println("Hay " + numChicos + " Chicos y " + numChicas + " Chicas dentro.\n");
	}
	
	public synchronized void entraChico() {
		numChicos++;
		System.out.println("Ha entrado un Chico.");
	}
	
	public synchronized void entraChica() {
		numChicas++;
		System.out.println("Ha entrado una Chica.");
	}
	
	public void saleChico() {
		numChicos--;
		System.out.println("Ha salido un Chico.");
	}
	
	public void saleChica() {
		numChicas--;
		System.out.println("Ha salido una Chica.");
	}
	
}
