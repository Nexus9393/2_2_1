package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      Query query = sessionFactory.getCurrentSession()
              .createQuery("from User u join fetch u.car c", User.class);
      return query.list();
   }

   @Override
   public User findUserByCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession()
              .createQuery("from User u join fetch u.car c where c.model = :model and c.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return (User) query.uniqueResult();
   }
}
