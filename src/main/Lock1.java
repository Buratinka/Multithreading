package main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock1
{
    public static void main(String[] args)
    {
        Resource resource = new Resource();
        resource.i = 5;
        resource.j = 5;

        MyThread myThread = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread.resource = resource;
        myThread2.resource = resource;
        myThread.start();
        myThread2.start();



        try {
            myThread.join();
            myThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(resource.i + " " + resource.j);

    }

    static class MyThread extends Thread
    {
        Resource resource;
        @Override
        public void run()
        {
            resource.changeI();
            resource.changeJ();
        }
    }
}
class Resource
{
    int i,j;
    Lock lock = new ReentrantLock();

    public void changeI()
    {
        lock.lock();
        int i = this.i;
        ++i;
        this.i = i;
    }
    public void changeJ()
    {
        int j = this.j;
        ++j;
        this.j=j;
        lock.unlock();
    }
}

