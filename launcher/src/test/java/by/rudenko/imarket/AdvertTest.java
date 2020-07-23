package by.rudenko.imarket;

import by.rudenko.imarket.dto.AdvertDTO;
import by.rudenko.imarket.dto.AdvertShortDTO;
import by.rudenko.imarket.enumes.Enumes;
import by.rudenko.imarket.model.Advert;
import by.rudenko.imarket.model.AdvertRank;
import by.rudenko.imarket.model.AdvertTopic;
import by.rudenko.imarket.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;
import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =
        {LauncherTestConfiguration.class,
                IMarketConfig.class,
                Launcher.class})
@WebAppConfiguration
@Transactional
@EnableWebMvc
public class AdvertTest {

    @Autowired
    private  AdvertService advertService;
    @Autowired
    private  AdvertDao advertDAO ;
    @Autowired
    private  ModelMapper modelMapper;


    //тестируем через DAO
    @Test
    public void addNewAdvertDAOTst() {

        advertDAO.save(newAdvert(1L));
        Advert advert = advertDAO.findByID(1L);
        Assert.assertNotNull(advert);
    }

    //тестируем через слой сервиса
    @Test
    public void addNewAdvertServiceTst() {

        advertService.addNewAdvert(newAdvertShortDTO(1L));
        AdvertDTO advert = advertService.findById(1L);
        Assert.assertNotNull(advert);
    }

    //новый тест
    @Test
    public void advertCountTst() {

   /*     advertDAO.save(newAdvert(1L));
        advertDAO.save(newAdvert(2L));
        advertDAO.save(newAdvert(3L));
*/
        Long expected = advertService.entityCount();
        Long actual = 18L; //сколько записей по факту в базе
        Assert.assertEquals(expected,actual);

    }

    //вспомогательные методы создания новых объявлений
    private Advert newAdvert (Long id){
        User user = new User(1L, "user-"+id, "pass-"+id, Enumes.UserRole.ROLE_USER);
        AdvertTopic advertTopic = new AdvertTopic(1L, "Phones", "Mobile");
        AdvertRank advertRank = new AdvertRank(1L, 10 , Enumes.RankName.PRIOR);
        LocalDate date = LocalDate.now();

        return new Advert(id, user, advertTopic, advertRank,
                Enumes.AdverType.SELL, "Sell nice phone "+id,
                100, date, Enumes.AdverStatus.NEW  );
    }

    private AdvertShortDTO newAdvertShortDTO (Long id){
        return new AdvertShortDTO(id, 1L, 1L, 1L,
                Enumes.AdverType.SELL, "Sell nice phone",
                100, LocalDate.now(), Enumes.AdverStatus.NEW );

    }
}
