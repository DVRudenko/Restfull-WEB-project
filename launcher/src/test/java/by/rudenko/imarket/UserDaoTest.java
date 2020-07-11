package by.rudenko.imarket;

import by.rudenko.imarket.model.User;
import by.rudenko.imarket.enumes.Enumes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.security.Security;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =
                {LauncherTestConfiguration.class,
                IMarketConfig.class,
                Security.class,
                Launcher.class})
@WebAppConfiguration
@Transactional
@EnableWebMvc
public class UserDaoTest {

    @PersistenceContext
    protected EntityManager em;
    @Autowired
    private UserDao userDao;

    @Test
    public void findByUsernameTst() {

        User newUser = new User(1L, "user10", "password", Enumes.UserRole.ROLE_USER);
        userDao.save(newUser);
        User user = userDao.findByUsername("user10");
        Assert.assertNotNull(user);
    }


}
