package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
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
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }


   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<User> typedQuery
              = sessionFactory.getCurrentSession().createQuery(
                      "SELECT u FROM User u JOIN FETCH u.car c WHERE c.model = :model AND c.series = :series", User.class);
      typedQuery.setParameter("model", model);
      typedQuery.setParameter("series", series);
      return typedQuery.setMaxResults(1).getSingleResult();


   }





}
