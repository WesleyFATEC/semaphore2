package control;

public class Corredor {
	
	
	
	
	
	
	
}

	private final int id;
	private int time;
	
	public ThreadBd(int id){
		this.id = id;
				
	}
	
	public void run() {
		Random rd = new Random();
		ProcessoBd bdProcess = new ProcessoBd();
		try {
		if (id % 3 ==1) {
			time = 1000;
			ThreadA(rd,bdProcess,time);
		}else if (id % 3 == 2) {
			time = 1500;
			ThreadB(rd,bdProcess,time);
		}else {
			time = 1500;
			ThreadC(rd,bdProcess,time);
		}
		}catch(InterruptedException e) {
            e.printStackTrace();
        }
	}

	private void ThreadC(Random rd, ProcessoBd bdProcess, int time2) throws InterruptedException {
		Calc(rd,0.2,1.0);
		bdProcess.BdTrasaction(id, time2);
		
	}

	private void ThreadB(Random rd, ProcessoBd bdProcess, int time2) throws InterruptedException {
		Calc(rd,1.0,2.0);
		bdProcess.BdTrasaction(id, time2);
		
	}

	private void ThreadA(Random rd, ProcessoBd bdProcess, int time2) throws InterruptedException {
		Calc(rd,0.2,1.0);
		bdProcess.BdTrasaction(id, time2);
		
	}
	private void Calc(Random rd, double min, double max) throws InterruptedException{
		double time = min + (max - min ) * rd.nextDouble();
		DecimalFormat df = new DecimalFormat("#.00");
		String timeFormat = df.format(time);
		System.out.println("A Thread" +  id + "está realizando cálculos por" + timeFormat + "seg");
		Thread.sleep((long) (time*1000));
	}
}
