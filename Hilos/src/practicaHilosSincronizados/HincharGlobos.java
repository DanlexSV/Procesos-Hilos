package practicaHilosSincronizados;

public class HincharGlobos extends Thread {

	Globos gl;
	int num;

	public HincharGlobos(Globos globo, int numero) {
		gl = globo;
		num = numero;
		setName("HinchaGlobos " + numero);
		start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int cantGlobo;
		boolean haEstallado;

		while (true) {
			if ((cantGlobo = gl.darGlobo()) == -1)
				break;
			do {
				try {
					Thread.sleep(1500);
				} catch (Exception e) {
					// TODO: handle exception
				}
				haEstallado = gl.hincharGlobo(cantGlobo);
			} while (!haEstallado);
		}

	}

}
