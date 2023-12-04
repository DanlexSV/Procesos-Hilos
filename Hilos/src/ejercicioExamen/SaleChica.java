package ejercicioExamen;

public class SaleChica extends Thread {

	Personas ppl;

	public SaleChica(Personas ppl) {
		super();
		this.ppl = ppl;
		start();
	}
	
	@Override
	public void run() {
		do {
			try {
				Thread.sleep(8000);
				ppl.saleChica();
				ppl.Aforo();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (ppl.personasDentro() < ppl.getMaxPersonas());
	}
	
}
