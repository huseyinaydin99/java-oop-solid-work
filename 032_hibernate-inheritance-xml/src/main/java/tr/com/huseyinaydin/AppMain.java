package tr.com.huseyinaydin;

import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.entity.Owner;
import tr.com.huseyinaydin.entity.Person;
import tr.com.huseyinaydin.util.HibernateUtil;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class AppMain {

    public static void main(String[] args) {
        Person person1 = new Person("Hüseyin","Aydın");
        //person.setPersonId(1L);

        Employee employee1 = new Employee("Zımzımettin","Kocakaplan", "Hayvanat bahçesi",
                "1111", "abc@gmail.com",
                10000, new Date(), 1453L);

        Owner owner1 = new Owner("Hasan","Fefer","Mırtanlılı",
               "Project Manager" );

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(person1);
            session.save(owner1);
            session.save(employee1);
            transaction.commit();
        } catch (HibernateError e) {
            System.out.println("HibernateError : " + e.getMessage());
        } finally {
            session.close();
        }
    }
}