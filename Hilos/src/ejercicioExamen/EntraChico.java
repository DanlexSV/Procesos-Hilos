package ejercicioExamen;

public class EntraChico extends Thread {

	Personas ppl;
	
	public EntraChico(Personas ppl) {
		super();
		this.ppl = ppl;
		start();
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(2000);
				ppl.entraChico();
				ppl.Aforo();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (ppl.personasDentro() < ppl.getMaxPersonas());
	}
	
}
