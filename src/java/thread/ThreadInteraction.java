package thread;

public class ThreadInteraction {
	public static void main(String[] args){
		ThreadA a = new ThreadA();
		a.start();
		
		// For a thread to call wait() or notify(), the thread must own the lock for that object.
		synchronized(a){ 
			try{
				System.out.println("Waiting to complete");
				a.wait();
			} catch (InterruptedException e){
				System.out.println("Exception " );
			} finally {
				System.out.println("Total is: " + a.total);
			}
		}
	}
}

class ThreadA extends Thread{
	int total = 0;
	
	@Override
	public void run(){
		synchronized(this){
			for (int i=0; i<=100; i++){
				total += i;
			}

			notifyAll();
		}
	}
}


/** common structure
 
   synchronized(anotherObject) { // this has the lock on anotherObject
     try {
       anotherObject.wait();
         // the thread releases the lock and enter waiting stage
     } catch(InterruptedException e){}
   }
	
	The preceding code waits until notify()/notifyAll() is called on anotherObject.
 */

