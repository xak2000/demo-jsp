package demo.it;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import demo.DemoJspApplication;
import demo.Profiles;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoJspApplication.class)
@WebAppConfiguration
@ActiveProfiles(Profiles.DEV)
public abstract class AbstractIntegrationTests {

}
