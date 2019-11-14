package just.fo.fun;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Slf4j
@SpringBootApplication
public class FunApplication {

    private static final Logger LOG = LoggerFactory.getLogger(FunApplication.class);


    private static final String FOR_FUN_LOGO =
            "\n\t                                                              \n" +
                    "\t███████▒ ████████▒  ███████▒     ███████▒ ██▒   ██▒  ██   ██▒  \n" +
                    "\t██▒      ██▒   ██▒  ██▒   ██     ██▒      ██▒   ██▒  ███  ██▒  \n" +
                    "\t███████▒ ██▒   ██▒  ███████▒     ███████▒ ██▒   ██▒  ██▒█▒██▒  \n" +
                    "\t██▒      ██▒   ██▒  ███▒██       ██▒      ██▒   ██▒  ██  ███▒  \n" +
                    "\t██▒      ████████▒  ██▒  ██▒     ██▒      ████████▒  ██   ██▒  \n";


    public static void main(String[] args) {

      ConfigurableApplicationContext context = SpringApplication.run(FunApplication.class, args);
      showLogo(context);

    }


    public static void showLogo(ApplicationContext context) {
        Environment env = context.getEnvironment();

        String hostAddress = "unKnoWn";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            LOG.error("local host name could not be resolved", ex);
        }

        final String applicationName = env.getProperty("spring.application.name") != null
                ? env.getProperty("spring.application.name") : "FOR FUN";
        final String applicationServerPort = env.getProperty("server.port") != null ? env.getProperty("server.port") : "8080";

        LOG.info(
                FOR_FUN_LOGO
                        + "\n==========================================================\n\t"
                        + "Application '{}' is running! Access URLs:\n\t"
                        + "Local: \t\thttp://127.0.0.1:{}\n\t"
                        + "External: \thttp://{}:{}\n"
                        + "==========================================================",
                applicationName,
                applicationServerPort,
                hostAddress, applicationServerPort);

    }

}


