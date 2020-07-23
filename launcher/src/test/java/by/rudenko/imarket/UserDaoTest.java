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

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =
                {LauncherTestConfiguration.class,
                IMarketConfig.class,
                Launcher.class})
@WebAppConfiguration
@Transactional
@EnableWebMvc
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void saveUserTst() {

        User newUser = new User(1L, "user1", "pass1", Enumes.UserRole.ROLE_USER);
        userDao.save(newUser);
        User user = userDao.findByID(1L);
        Assert.assertNotNull(user);
    }
    //новый тест
    @Test
    public void findByUsernameTst() {

        User newUser = new User(2L, "user2", "pass2", Enumes.UserRole.ROLE_USER);
        userDao.save(newUser);
        User user = userDao.findByUsername("user2");
        Assert.assertEquals(user,newUser);
    }




}
