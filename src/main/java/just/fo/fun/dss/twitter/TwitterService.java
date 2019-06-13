package just.fo.fun.dss.twitter;

import just.fo.fun.dss.DssModel;
import just.fo.fun.dss.DssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterService {


    @Autowired
    private DssService dssService;

    @Autowired
    private POCWithoutDevkitTest pocWithoutDevkitTest;


    public List<TwitterResponseData> calculate(String query){

        boolean russianWord = isRussianWord(query);

        String locale =  russianWord ? "ru" : "en";

        List<TwitterDataItem> twitts = pocWithoutDevkitTest.executeGetRequest("https://api.twitter.com/1.1/search/tweets.json?q=" + query + "&result_type=recent&lang=" + locale).getStatuses();

        List<TwitterResponseData> responseData = new ArrayList<>(twitts.size());

        for (TwitterDataItem twitterDataItem : twitts){

            DssModel calculate = dssService.calculate(twitterDataItem.getText(), russianWord);

            TwitterResponseData twitterResponseData = new TwitterResponseData(twitterDataItem.getText(),
                    twitterDataItem.getUser().getName(),
                    twitterDataItem.getCreated_at(),
                    calculate.getNegativeSelectedWords(),
                    calculate.getPositiveSelectedWords(),
                    calculate.getNegativeWordCount(),
                    calculate.getPositiveWordCount(),
                    calculate.getNegativePercent(),
                    calculate.getPositivePercent(),
                    calculate.getNormalizedWords(),
                    calculate.getNeutralWordCount(),
                    calculate.getOriginalWords(),
                    calculate.getNeutralPercent()
            );
            responseData.add(twitterResponseData);

        }
        return responseData;

    }


    public static boolean isRussianWord(String args) {

        if (StringUtils.isEmpty(args)){
            return false;
        }

        return  (args.charAt(0) > 1039 && args.charAt(0) < 1104);
    }
}
