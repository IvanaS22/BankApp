package bank.model;

import bank.hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public abstract class AbstractBankAccount {

    public BankAccount getThis() {
        return (BankAccount) this;
    }

    public void save() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(this);
            session.getTransaction().commit();

        } catch (HibernateException he) {
            throw new RuntimeException(he.getMessage());
        } finally {
            if (session != null) {
                session.close();

            }
        }
    }

    public void update() {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(this);
            session.getTransaction().commit();

        } catch (HibernateException he) {
            throw new RuntimeException(he.getMessage());
        } finally {
            if (session != null) {
                session.close();

            }

        }
    }

    public BankAccount get() {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            BankAccount bankAccount = (BankAccount) session.get(BankAccount.class, getThis().getAccountNumber());
            return bankAccount;

        } catch (HibernateException he) {
            throw new RuntimeException(he.getMessage());
        } finally {
            if (session != null) {
                session.close();

            }
            return null;
        }
    }

    public void delete() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(this);
            session.getTransaction().commit();

        } catch (HibernateException he) {
            throw new RuntimeException(he.getMessage());
        } finally {
            if (session != null) {
                session.close();

            }
        }
    }

    public  static List<BankAccount>loadAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from BankAccount");
            List<BankAccount> bankAccounts = query.list();
            return bankAccounts;

        } catch (HibernateException he) {
            throw new RuntimeException(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static boolean transferMoney(BankAccount fromAccount, BankAccount toAccount, double ammount) {
        if (fromAccount == null || toAccount == null || ammount <= 0) {
            return false;
        }
        if (fromAccount.getAccountNumber().equals(toAccount.getAccountNumber())) {
            return false;

        }
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            if (fromAccount.getAmmount() < ammount) {
                System.out.println("Zao nam je,nemate dovoljno sredstava na racunu");
               return false;
               
            }
            double trenutniIznosFromAccount=fromAccount.getAmmount();
            fromAccount.setAmmount(trenutniIznosFromAccount-ammount);
            
            double stariIznosToAccount=toAccount.getAmmount();
            toAccount.setAmmount(stariIznosToAccount+ammount);
            session.update(fromAccount);
            session.update(toAccount);
            session.getTransaction().commit();
           return true;
        } catch (HibernateException he) {
            throw new RuntimeException(he.getLocalizedMessage());
        } finally {
            if (session != null) {
                session.close();

            }
        }
    }
}