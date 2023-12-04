package ejercicioRepaso2;

public class Ratones {
	
	public Ratones() {
		super();
	}
	
	public boolean entraMacho(int Hembra, int Macho) {
		if ((Hembra - Macho) == 2)
			return true;
		return false;
	}
	
	public boolean entraHembra(int Hembra, int Macho) {
		if (!entraMacho(Hembra, Macho))
			return true;
		return false;
	}

}
