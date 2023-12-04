package ejercicioExamen;

public class EntraChica extends Thread {

	Personas ppl;
	
	public EntraChica(Personas ppl) {
		super();
		this.ppl = ppl;
		start();
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(3000);
				ppl.entraChica();
				ppl.Aforo();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (ppl.personasDentro() < ppl.getMaxPersonas());
	}
	
}
