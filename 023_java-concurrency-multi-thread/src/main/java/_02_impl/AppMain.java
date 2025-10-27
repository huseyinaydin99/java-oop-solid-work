package _02_impl;

//inner
class MyBaseThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread sınıfını implemente eden donatan/imzalayan MyBaseThread sınıfının run metodu çalışıyor: "
                + Thread.currentThread().getName());
    }
}

public class AppMain {

    public static void main(String[] args) {
        MyBaseThread myBaseThread = new MyBaseThread();
        System.out.println("Thread sınıfını implemente eden donatan/imzalayan MyBaseThread iş akışı henüz başlatılmadı.");

        Thread thread = new Thread(myBaseThread);
        thread.start();
        System.out.println("Thread sınıfını implemente eden donatan/imzalayan MyBaseThread ahanda başlatıldı inanmazsan aha dayıya sor..");

    }
}