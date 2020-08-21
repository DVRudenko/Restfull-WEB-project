import by.rudenko.imarket.AdvertRankDao;
import by.rudenko.imarket.dto.AdvertRankDTO;
import by.rudenko.imarket.impl.AdvertRankServiceImpl;
import by.rudenko.imarket.model.AdvertRank;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @Mock
    private AdvertRankDao advertRankDao;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AdvertRankServiceImpl advertRankService;

    @Test
    public void addNewAdvertRankTest(){

        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any(Class.class))).thenReturn(new AdvertRank());
        Mockito.when(advertRankDao.save(Mockito.any())).thenReturn(true);

        boolean result = advertRankService.addNewAdvertRank(new AdvertRankDTO());

        Mockito.verify(modelMapper,  Mockito.times(1)).map(Mockito.any(), Mockito.any(Class.class));
        Mockito.verify(advertRankDao, Mockito.times(1)).save(Mockito.any());

        Mockito.reset(modelMapper, advertRankDao);

        Assert.assertTrue(result);

    }

}
