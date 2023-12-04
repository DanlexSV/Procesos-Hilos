package claseBar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bar {

	volatile int clientEspera = 0, dineroCaja = 50, pagos = 0;
	int tipoBebidas = 5, maxUnidades = 8, maxStock = 20, maxAforo = 20, tiempoActual = 0, TimeMilli = 100;
	volatile Object[][] Bebidas = new Object[tipoBebidas][2];
	volatile Object[][] Pedidos;

	public Bar() {
		Pedidos = new Object[maxAforo][2];

		for (int i = 0; i < tipoBebidas; i++) {
			Bebidas[i][0] = "Bebida " + (i + 1);
			Bebidas[i][1] = maxUnidades;
		}

		for (int i = 0; i < maxAforo; i++) {
			Pedidos[i][0] = "Cliente " + (i + 1);
			Pedidos[i][1] = 0;
		}

		startContadorTiempo();
	}

	//Este metodo lo usaria la clase Cliente para poder hacer el pedido
	public synchronized boolean hacerPedido(String cliente, int bebida) {
		//Se recorre la lista de Pedidos para atender al Cliente
		for (int i = 0; i < maxAforo; i++) {
			//Guardamos en una variable String el nombre de los clientes de la lista
			String clienteActual = (String) Pedidos[i][0];
			
			//Comprobamos si el cliente esta en la lista
			if (clienteActual.equals(cliente)) {
				
				//Guardamos en la lista de pedidos el nombre de la bebida
				Pedidos[i][1] = bebida;
				//Aumentamos el contador de clientes en espera
				clientEspera++;
				System.out.println("El cliente: " + cliente + " ha pedido la bebida " + bebida);
				
				return true;
			}
		}
		
		return false;
	}

	//Metodo usado por la clase Camarero la cual pondra que camarero lo atiende, y el numero del cliente
	public synchronized boolean entregarBebida(String camarero, int num) {
		//Declaramos una variable para identificar el cliente.
		String cliente = "Cliente " + num;
		
		//Recorre la lista de Pedidos para entregar la bebida al Cliente
		for (int i = 0; i < maxAforo; i++)
			
			//Comprobamos que el Cliente coincida con la lista
			if (Pedidos[i][0].equals(cliente)) {
				
				//Guardamos en una variable el tipo de bebida del cliente
				int numBebida = (int) Pedidos[i][1];
				
				//Comprobamos que sea un valor dentro de la lista
				if (numBebida > 0) {
					
					//Guardamos en una variable la cantidad de stock que tenga la bebida
					int stock = (int) Bebidas[numBebida - 1][1];
					
					//Comprobamos que si hay stock de la bebida
					if (stock > 0) {
						
						//Vaciamos la lista de pedidos para otro cliente
						Pedidos[i][1] = 0;
						
						//Reducimos la cantidad de stock de la bebida seleccionada
						Bebidas[numBebida - 1][1] = stock - 1;
						
						System.out.println("Camarero " + camarero + " ha entregado la bebida a " + cliente);
						clientEspera--;
						dineroCaja += 5;
						
						return true;
					}
				}
			}
		return false;
	}

	//Metodo usado por la clase Cliente para saber si ya ha sido entregada la bebida
	public synchronized boolean bebidaEntregada(String cliente) {
		//Recorre la lista de Pedidos para entregar la bebida al Cliente
		for (int i = 0; i < maxAforo; i++) {
			
			//Guardamos el nombre del cliente que hizo el pedido en una variable
			String clienteActual = (String) Pedidos[i][0];
			
			//Comprobamos que el cliente actual sea el que ha hecho el pedido
			if (clienteActual.equals(cliente)) {
				
				//Guardamos el tipo de bebida en una variable
				int numBebida = (int) Pedidos[i][1];
				
				//Comprobamos si se ha entregado la bebida o no
				if (numBebida == 0)
					return true;
				return false;
			}
		}
		return false;
	}

	//Metodo usado por la clase Proveedor para actualizar Stock
	public synchronized boolean reponerStock(String proveedor) {
		//Recorre la lista de Bebidas para reponer Stock
		for (int i = 0; i < tipoBebidas; i++) {
			
			//Guardamos en una variable el stock antes de actualizar el stock
			int stockActual = (int) Bebidas[i][1];
			//Repone stock en todas las bebidas sin importar la cantidad anterior
			Bebidas[i][1] = maxUnidades;
			
			//Guardamos en una variable la cantidad de bebidas agregadas
			int gastoStock = maxUnidades - stockActual;
			//Se descuenta de la caja la cantidad de bebidas repuestas
			dineroCaja -= (2 * gastoStock);
		}
		System.out.println(proveedor + "ha venido a reponer las bebidas.");
		
		return true;
	}

	//Metodo usado por el mismo Bar para contabilizar el tiempo abierto del mismo
	public synchronized void startContadorTiempo() {
	    //Se crea un servicio de ejecución programada con el hilo actual
		ScheduledExecutorService horario = Executors.newScheduledThreadPool(1);
		
		//Se programa la ejecucion periodica del contador
		horario.scheduleAtFixedRate(() -> {
			System.out.println(tiempoActual);
			tiempoActual++;

			//Verifica si se ha alcanzado el límite de tiempo (300 unidades)
			if (tiempoActual > 300) {
				
				//Detenemos la ejecucion del contador
				horario.shutdown();
				
				//Ceramos el bar
				System.out.println("CERRADO");
				//Descontamos los 50€ de gastos fijos
				dineroCaja -= 50;
				System.out.println("Cubriendo todos los gastos, al bar le ha quedado un total de: " + dineroCaja + "€.");
			}
		}, 0, TimeMilli, TimeUnit.MILLISECONDS);
	}

}
