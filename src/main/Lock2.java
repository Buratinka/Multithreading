package main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock2
{
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {

        MyThread thread = new MyThread();
        MyThread2 thread2 = new MyThread2();

        thread.start();
        thread2.start();

    }

    static class MyThread extends Thread
    {
        @Override
        public void run()
        {
           lock.lock();
            System.out.println(getName() + " start working");
            try
            {
                sleep(50);
            }catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
            System.out.println(getName() + " stop working");
            lock.unlock();
            System.out.println(getName() + " lock is released");
        }
    }

    static class MyThread2 extends Thread
    {
        @Override
        public void run()
        {
            System.out.println(getName() + " start working");
            while(true)
            {
                if(lock.tryLock())
                {
                    System.out.println(getName() + "working");
                    break;
                }else {
                    System.out.println(getName() + " waiting");
                }
            }
        }
    }

}
