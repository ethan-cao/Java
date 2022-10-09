package thread;

import java.util.List;
import java.util.ArrayList;

public class ThreadWaitNotifyTest {
	public static void main(String[] args){
	    Machine m = new Machine();
	    m.start();

	    m.addJob(new MachineJob());
    }
}

class Machine extends Thread{
    List<MachineJob> jobs = new ArrayList<>();
    
    public void addJob(MachineJob job){
        /**
         * use synchronized block for modifying shared resource jobs
         * acquire the lock of jobs object, notifyAll() in the very end
         */
        synchronized (jobs){
            System.out.println("adding, getting lock ");
            jobs.add(job);
            jobs.notifyAll();
            System.out.println("finish adding, jobs notifies the waiting thread");
        }
    }

/**
	the run() method just keeps looking for any jobs on the list.
	If there is no job, it will start waiting.
	If it's notified, it stops waiting and then re-check the loop condition: Is the list still empty? 
 */
    public void run(){
        while(true){
            synchronized(jobs){

                while (jobs.isEmpty()){    // This is for preventing spontaneous wake-up
                    try {
                        System.out.println("no jobs, start waiting, release lock");
                        jobs.wait();   // lock of jobs is released from the executing thread
                    } catch (InterruptedException e){
                        System.out.print("Exception");
                    }
                }
                
                // If job list is not empty, then send the job to machine
                MachineJob job = jobs.remove(0);
                System.out.println("there was job, removed");
            }
        }
    }
}

class MachineJob {}

