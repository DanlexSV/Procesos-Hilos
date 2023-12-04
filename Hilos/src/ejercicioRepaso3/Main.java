package ejercicioRepaso3;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ratones rt = new Ratones();
		Entrar entrar = new Entrar(rt);
		Asesinato asesinato = new Asesinato(rt);
		
		entrar.start();
		asesinato.start();
		
	}

}
