package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = entityManager.createQuery("from User", User.class).getResultList();

        return allUsers;
    }

    //данный метод и сохраняет и изменяет объекты. У нового объекта id = 0, тогда persist, иначе merge (с присвоением текущего id)
    @Override
    public void saveUser(User user) {
        if (user.getId() == 0) {
            entityManager.persist(user);
        } else {
            user.setId(user.getId());
            entityManager.merge(user);
        }
    }

    @Override
    public User getUser(int id) {
        User userById = entityManager.find(User.class, id);

        return userById;
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
