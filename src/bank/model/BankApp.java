
package bank.model;
//KORACI:

import bank.hibernate.HibernateUtil;
import org.hibernate.Session;

/*
1. Kreiranje baze bank i tabele bank_account u workbenchu
2.Kreiranje Java App
3.Mapiranje bank_account tabele u Java klasu-> ORM:
3.1 Add HIbernate Library
3.2 Hibernate.cfg.xml- konfiguracija( na koju bazu se veze i koji dijalekt)
3.3 HIbernateUtil- tvornica session objekata
4. Mapiranje hibernate.reveng.xml prosli put smo kroz wizard sad cemo rucno da vidimo
: imali smo java klasu sa XML fajlom koji je sadrzavao podatke o mapiranju
: sada cemo imati Javu klasu sa anotacijama bez XML-a.(JPA )
5. Active record pattern- mozemo ubaciti tu crud operacije
abstract active record pattern- pravimo apstraktnu klasu koja ce imati pobrojane funkcije(kao DAO)
i onda iz BankAccount klase extendamo apstraktnu klasu
// sada smo htjeli to povezati sa swing aplikacijom pa nam treba jos i metoda transferMoney(AbstractBankAccount)

 */
public class BankApp {

    public static void main(String[] args) {
        // bez Abstract Active Record-a:
        BankAccount bankAccount= new BankAccount("132056312310",345);
       // Session session=HibernateUtil.getSessionFactory().openSession();
//       // session.save(bankAccount);
        
        // SA  ABstract Active Record-om:/ cilj mu je da prividino izbacimo session:
        bankAccount.save();
        
       
    
    }
    
}
