package controller;

import java.util.concurrent.Semaphore;

public class ThreadTriatlo extends Thread {

	private int id;
	private Semaphore semaforo;
	private Semaphore semaforo2;
	private static int podio[] = new int[25];
	private static int cont = 0;
	private int pontoT = 0;
	private int corrido = 0;
	private int ciclado = 0;
	private int ven = 250;

	public ThreadTriatlo(int id, Semaphore semaforo, Semaphore semaforo2) {
		this.id = id;
		this.semaforo = semaforo;
		this.semaforo2 = semaforo2;
	}

	@Override
	public void run() {
		corrida();
		try {
			semaforo.acquire();
			tiro();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		bicicleta();
		try {
			semaforo2.acquire();
			ponto();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} semaforo2.release();
		for(int i =0;i<25;i++) {
			System.out.println(podio[i]);
		}
	}

	private void ponto() {
		if(cont != 24) {
		podio[cont] = ven + pontoT;
		cont++;
		ven = ven - 10;
		} else {
			podio[cont] = 10 + pontoT;
		}
	}

	private void corrida() {
		System.out.println("Atleta nº" + id + " comecou a correr");
		while (corrido < 3000) {
			corrido += (int) ((Math.random() * 5) + 20);
			try {
				sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Atleta nº" + id + " terminou de correr");
	}

	private void tiro() {
		System.out.println("Atleta nº" + id + " vai atirar");
		for (int i = 0; i < 3; i++) {
			pontoT = pontoT + (int) (Math.random() * 11);
			try {
				sleep((long) ((Math.random() * 2501) + 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Atleta nº" + id + " terminou de atirar");
	}
	
	private void bicicleta() {
		System.out.println("Atleta nº" + id + " comecou a pedalar");
		while (ciclado < 5000) {
			ciclado += (int) ((Math.random() * 10) + 30);
			try {
				sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Atleta nº" + id + " terminou de pedalar");
	}
}
