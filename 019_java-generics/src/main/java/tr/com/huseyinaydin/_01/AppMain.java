package tr.com.huseyinaydin._01;

import java.util.ArrayList;

public class AppMain {

    public static void main(String[] args) {
        ArrayList myArrayList1 = new ArrayList();
        myArrayList1.add("ABCD");
        myArrayList1.add(100);
        myArrayList1.add(true);
        myArrayList1.add('A');
        myArrayList1.add(123.4);
        myArrayList1.add(123.4F);
        myArrayList1.add(1_000_000_000L);

        System.out.println(myArrayList1);

        System.out.println("-------------------------------");

        ArrayList<String> myArrayList2 = new ArrayList();

        String eklenecekVeri = "ABCD";
        myArrayList2.add(eklenecekVeri);

        System.out.println(myArrayList2);

        System.out.println("-------------------------------");
        PersonelList<String> myPersonelList1 = new PersonelList();
        myPersonelList1.listeyeEkle("Veli");
        myPersonelList1.listeyeEkle("Deli");
        System.out.println(myPersonelList1.listeyiGetir());

        System.out.println("-------------------------------");
        PersonelList<Integer> myPersonelList2 = new PersonelList();
        myPersonelList2.listeyeEkle(14);
        myPersonelList2.listeyeEkle(56);
        System.out.println(myPersonelList2.listeyiGetir());

        System.out.println("-------------------------------");
        PersonelList<Personel> myPersonelList3 = new PersonelList();

        Personel myPersonel1 = new Personel();
        myPersonel1.setAdi("Mehmet");
        myPersonel1.setSoyadi("Sarıçizmeli");
        myPersonel1.setDepartman("Çöpçü");
        myPersonel1.setDogumYili(1991);
        myPersonel1.setSicilno("1111111");
        myPersonel1.setTc("8888888888");
        myPersonel1.setMedeniDurum(true);
        myPersonel1.setCocukSayisi(11);

        Personel myPersonel2 = new Personel(
                "Hüseyin",
                "AYDIN",
                "123456789",
                "222222",
                "Yazılım",
                1994,
                false,
                0);

        myPersonelList3.listeyeEkle(myPersonel1);
        myPersonelList3.listeyeEkle(myPersonel2);
        System.out.println(myPersonelList3.listeyiGetir());
    }
}