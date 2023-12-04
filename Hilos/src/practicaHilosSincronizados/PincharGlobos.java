package practicaHilosSincronizados;

public class PincharGlobos extends Thread{
	Globos g;
	int num;
	
	public PincharGlobos(Globos globos, int numero) {
		g = globos;
		num = numero;
		setName("PinchaGlobos " + numero);
		start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean sinGlobos;
		do {
			try {
				Thread.sleep((int)(Math.random()*5000));
			} catch (Exception e) {
				// TODO: handle exception
			}
			sinGlobos = g.pincharGlobo();
		} while (!sinGlobos);
	}
}
