import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StreamUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mvc.xml")
public class SmartTest {
    @Test
    public void testConnect() throws Exception {
        //模拟http请求
        URL url = new URL("https://way.jd.com/turing/turing");
        //准备创建连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置请求方法
        connection.setRequestMethod("POST");
        //设置需要输出响应内容
        connection.setDoOutput(true);
        //构建需要传输的参数
        StringBuilder sb = new StringBuilder(100);
        sb.append("info=").append("今天天气怎么样");
        //传递参数
        connection.getOutputStream().write(sb.toString().getBytes("utf-8"));
        //连接对应的服务器,获取输入流
        connection.connect();
        String string = StreamUtils.copyToString(connection.getInputStream(), Charset.forName("utf-8"));
        System.out.println(string);


    }
}
