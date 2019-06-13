package just.fo.fun.dss.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Repository
public class TwitterRepository {

//    @Autowired
//    private RestTemplate restTemplate;


    @Value("&{host}")
    private String host;


    public TwitterData getTwitts(String query) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            final String baseUrl = "https://api.twitter.com/1.1/search/tweets.json?q=" + query + "&result_type=recent";
            URI uri = null;

            uri = new URI(baseUrl);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "OAuth oauth_consumer_key=\"UK9Rjchw68qHGowyLcRTZpT2V\",oauth_token=\"312595511-sYil7H05YZimrUQPCOH2Aw8HrDikrPTxmxSngJh9\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1560420786\",oauth_nonce=\"vFis7WLUCD9\",oauth_version=\"1.0\",oauth_signature=\"sOurGdLs24c4KTy8wzTI5YfezAQ%3D\"");

            HttpEntity requestEntity = new HttpEntity<>(null, headers);

            ResponseEntity<TwitterData> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, TwitterData.class);

            System.out.println("result = " + result.getBody());

            return result.getBody();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }


    }
}
