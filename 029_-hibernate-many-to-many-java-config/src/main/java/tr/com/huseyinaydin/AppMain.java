package tr.com.huseyinaydin;

import tr.com.huseyinaydin.entity.Address;
import tr.com.huseyinaydin.entity.Customer;
import tr.com.huseyinaydin.util.HibernateUtil;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppMain {

    public static void main(String[] args) {
        Customer customer1 = new Customer("Hüseyin", "Aydın");

        Customer customer2 = new Customer();
        customer2.setFirstName("Selami");
        customer2.setLastName("Zımbo");

        Address address1 = new Address();
        address1.setCountry("TR");
        address1.setCity("Ankara");
        address1.setState("Merkez");
        address1.setStreet("Abc");
        address1.setZip("111");

        Address address2 = new Address();
        address2.setCountry("TR");
        address2.setCity("İstanbul");
        address2.setState("Merkez");
        address2.setStreet("Def");
        address2.setZip("222");

        Address address3 = new Address();
        address3.setCountry("TR");
        address3.setCity("Adana");
        address3.setState("Merkez");
        address3.setStreet("Patlıyo herkess");
        address3.setZip("333");

        customer1.getAddresses().add(address1);
        customer1.getAddresses().add(address2);
        customer1.getAddresses().add(address3);

        customer2.getAddresses().add(address1);
        customer2.getAddresses().add(address2);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(customer1);
            session.save(customer2);
            transaction.commit();
        } catch (HibernateError e) {
            System.out.println("HibernateError : " + e.getMessage());
        } finally {
            session.close();
        }
    }
}