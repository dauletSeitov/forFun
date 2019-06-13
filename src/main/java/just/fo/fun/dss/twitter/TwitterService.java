package just.fo.fun.dss.twitter;

import just.fo.fun.dss.DssModel;
import just.fo.fun.dss.DssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterService {


    @Autowired
    private TwitterRepository repository;

    @Autowired
    private DssService dssService;


    public List<TwitterResponseData> calculate(String query){

        List<TwitterDataItem> twitts = repository.getTwitts(query).getStatuses();

        List<TwitterResponseData> responseData = new ArrayList<>(twitts.size());

        for (TwitterDataItem twitterDataItem : twitts){

            DssModel calculate = dssService.calculate(twitterDataItem.getText());

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
}
