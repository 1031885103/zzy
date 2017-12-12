import com._520it.crm.domain.Provinces;
import com._520it.crm.service.IProvinceService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mvc.xml"})
public class ProvincesTest {
    @Autowired
    private IProvinceService provinceService;

    @Test
    public void test() throws Exception {
        List<Provinces> provinces = provinceService.provinceList();
        System.out.println(JSON.toJSONString(provinces));

    }
}
