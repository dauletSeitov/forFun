package just.fo.fun.dss;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class DssModel {

    protected List<String> negativeSelectedWords;
    protected List<String> positiveSelectedWords;
    protected int negativeWordCount;
    protected int positiveWordCount;
    protected double negativePercent;
    protected double positivePercent;
    protected List<String> normalizedWords;
    protected int neutralWordCount;
    protected List<String> originalWords;
    protected double neutralPercent;


    public DssModel(List<String> negativeSelectedWords, List<String> positiveSelectedWords, int negativeWordCount, int positiveWordCount, double negativePercent, double positivePercent, List<String> normalizedWords, int neutralWordCount, List<String> originalWords, double neutralPercent) {
        this.negativeSelectedWords = negativeSelectedWords;
        this.positiveSelectedWords = positiveSelectedWords;
        this.negativeWordCount = negativeWordCount;
        this.positiveWordCount = positiveWordCount;
        this.negativePercent = negativePercent;
        this.positivePercent = positivePercent;
        this.normalizedWords = normalizedWords;
        this.neutralWordCount = neutralWordCount;
        this.originalWords = originalWords;
        this.neutralPercent = neutralPercent;
    }
}
