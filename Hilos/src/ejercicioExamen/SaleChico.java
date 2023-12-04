package ejercicioExamen;

public class SaleChico extends Thread {

	Personas ppl;

	public SaleChico(Personas ppl) {
		super();
		this.ppl = ppl;
		start();
	}
	
	@Override
	public void run() {
		do {
			try {
				Thread.sleep(7000);
				ppl.saleChico();
				ppl.Aforo();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} while (ppl.personasDentro() < ppl.getMaxPersonas());
	}
	
}
