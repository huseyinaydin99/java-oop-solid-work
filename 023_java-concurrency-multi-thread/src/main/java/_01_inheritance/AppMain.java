package _01_inheritance;

//inner
class MyBaseThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread sınıfını miras alıp genişleten MyBaseThread sınıfının run metodu çalışıyor: "
                + Thread.currentThread().getName());
    }
}

public class AppMain {

    public static void main(String[] args) {
        MyBaseThread myBaseThread = new MyBaseThread();
        System.out.println("Thread sınıfını miras alıp genişleten MyBaseThread iş akışı henüz başlatılmadı.");
        myBaseThread.start();
        System.out.println("Thread sınıfını miras alıp genişleten MyBaseThread ahanda başlatıldı inanmazsan aha dayıya sor..");
    }
}