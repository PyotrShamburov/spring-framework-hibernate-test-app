package by.tms.home.dao;

import by.tms.home.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(user);
    }

    public void deleteUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public User getUserById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.find(User.class, id);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from User where username =:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
