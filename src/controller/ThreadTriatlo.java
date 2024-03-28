package controller;

import java.util.concurrent.Semaphore;

public class ThreadTriatlo extends Thread {

	private int id;
	private Semaphore semaforo;
	private Semaphore semaforo2;
	private static int pontos[][] = new int[2][25];
	private static int c1 = 0;
	private static int c2 = 0;
	private static int premio = 250;
	private int pontoT = 0;
	private int corrido = 0;
	private int ciclado = 0;

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
			podio();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo2.release();
		}
		if (pontos[0][24] != 0) {
			sort();
			for (int i = 0; i < 25; i++) {
				System.out.print(pontos[0][i] + " ");
			}
			System.out.println();
			for (int i = 0; i < 25; i++) {
				System.out.print(pontos[1][i] + " ");
			}
		}
	}

	private void sort() {
		for (int i = 0; i < 25 - 1; i++)
			for (int j = 0; j < 25 - i - 1; j++)
				if (pontos[1][j] < pontos[1][j + 1]) {
					int temp = pontos[1][j];
					pontos[1][j] = pontos[1][j + 1];
					pontos[1][j + 1] = temp;
					int temp2 = pontos[0][j];
					pontos[0][j] = pontos[0][j + 1];
					pontos[0][j + 1] = temp2;
				}
	}

	private void podio() {
		if (c2 == 0) {
			pontos[c1][c2] = id;
			pontos[c1 + 1][c2] = premio + pontoT;
			c2++;
			premio = premio - 10;
		} else {
			pontos[c1][c2] = id;
			pontos[c1 + 1][c2] = premio + pontoT;
			c2++;
			premio = premio - 10;
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
