package control;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCavaleiros extends Thread{
	private final int distTot;
	private static int distPerc;
	private final int id;
	private static boolean tocha;
	private static boolean pedra;
	private int portaCerta;
	private static boolean portasLivres [] = new boolean[4];
	

	public ThreadCavaleiros(int id, int distPerc,int distTot, int portaCerta){
		this.id = id;
		this.distPerc = distPerc;
		this.distTot = distTot;
		this.portaCerta = portaCerta;
	}
	
	public void run() {
		Random rd = new Random();
		boolean bonus = false;
		final Semaphore semaphore = new Semaphore(1);
		while (distPerc < distTot)
		{	
			if (distPerc <= 500) {
				distPerc = rd.nextInt(4-2+1) + 2;
				try
				{
					semaphore.acquire();
					if (tocha & !bonus)
					{
					distPerc += 2;
					bonus = true;
					System.out.println("O cavaleiro " + id + " pegou a tocha!");
					}
				}
				catch (Exception e) 
				{
					System.out.println( e.getMessage());
				}
				finally
				{
					System.out.println("O cavaleiro "+ id +" andou"+ distPerc);
					semaphore.release();
				} 
				
		}
			
			else if (distPerc <= 1500) {
				distPerc = rd.nextInt(4-2+1) + 2;
				try
				{
					semaphore.acquire();
					if (pedra & !bonus)
					{
						System.out.println("O cavaleiro " + id +" pegou a pedra!");
					distPerc += 2;
					bonus = true;
					}
				}
				catch (Exception e) 
				{
					System.out.println( e.getMessage());
				}
				finally
				{
					System.out.println("O cavaleiro "+ id +" andou"+ distPerc);
					semaphore.release();
				} 
				
				
			}
			else
				{
				distPerc = rd.nextInt(4-2+1) + 2;
				distPerc += 2;
				System.out.println("O cavaleiro "+ id +" andou"+ distPerc);
				}
			
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		int escolha;
		
		try {
			semaphore.acquire();
			do {
			escolha = rd.nextInt(5-1)+1;
			}
			while (!portasLivres[escolha]);
			if (escolha == portaCerta) {
				System.out.println("O cavaleiro venceu!!!");
			}
			else {
				System.out.println("O cavaleiro morreu");
			}
			
		} catch (InterruptedException e) 
		{
			System.out.println(e.getMessage());
		}
		finally {
			semaphore.release();
		}	
			
			
	
	}
	
	}

			
		
	

			
			
		
		
		
	
