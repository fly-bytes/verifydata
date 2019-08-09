package happy.test;

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
    public void execute() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/get")
                .param("name", "123")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MvcResult mvcResult1 = mockMvc.perform(request).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(new String(content.getBytes(), "utf-8"));
    }
}
