package just.fo.fun.dss;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class DssModel {

    private List<String> negativeSelectedWords;
    private List<String> positiveSelectedWords;
    private int negativeWordCount;
    private int positiveWordCount;
    private double negativePercent;
    private double positivePercent;
    private List<String> normalizedWords;


}
