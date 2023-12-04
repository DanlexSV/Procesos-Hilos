package practicaHilosSincronizados;

public class InicioMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Globos globos = new Globos();
		
		for (int i = 1; i <= 5; i++)
			new HincharGlobos(globos, i);
		for (int i = 1; i <= 5; i++)
			new PincharGlobos(globos, i);
		
	}

}
