package happy.test;

import com.alibaba.fastjson.JSON;
import happy.test.bean.User;
import happy.test.bean.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = TestController.class)
public class DemoApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testParm() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/get")
                .param("names", "411121199405284012")
                .param("b", "true")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        System.out.println(new String(mvcResult.getResponse().getContentAsString().getBytes(), "utf-8"));
    }

    @Test
    public void testParmPathVariable() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/gets/1888/end");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        System.out.println(new String(mvcResult.getResponse().getContentAsString().getBytes(), "utf-8"));
    }

    @Test
    public void testParmJson() throws Exception {
        List<User> users = new ArrayList<>();
        User u = new User("zhangsan", 1, new Date());
        List<String> ids = Arrays.asList("1", "2", "123");
        UserInfo u1 = new UserInfo("lisi", 2);
//        u.setUserInfo(u1);
        users.add(u);
//        u.setIds(ids);
        RequestBuilder request = MockMvcRequestBuilders.post("/postJson")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(JSON.toJSONString(users))
                .param("email", "1@qq.com");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        System.out.println(new String(mvcResult.getResponse().getContentAsString().getBytes(), "utf-8"));
    }
}
