package view;

import java.util.concurrent.Semaphore;

import controller.ThreadTriatlo;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(5);
		Semaphore semaforo2 = new Semaphore(1);

		for (int id = 1; id <= 25; id++) {
			Thread competir = new ThreadTriatlo(id, semaforo, semaforo2);
			competir.start();
		}
		

	}

}