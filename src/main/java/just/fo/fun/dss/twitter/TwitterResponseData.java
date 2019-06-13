package just.fo.fun.dss.twitter;

import just.fo.fun.dss.DssModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwitterResponseData extends DssModel{
    
    private String text;
    private String userName;
    private String date;

    public TwitterResponseData(String text, String name, String created_at, List<String> negativeSelectedWords, List<String> positiveSelectedWords, int negativeWordCount, int positiveWordCount, double negativePercent, double positivePercent, List<String> normalizedWords, int neutralWordCount, List<String> originalWords, double neutralPercent) {
        super( negativeSelectedWords,  positiveSelectedWords, negativeWordCount, positiveWordCount, negativePercent, positivePercent, normalizedWords, neutralWordCount, originalWords, neutralPercent);
    
        this.text = text;
        this.userName = name;
        this.date = created_at;
    
    }
}
