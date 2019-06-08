package just.fo.fun.dss;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.LinkedList;

@Data
@AllArgsConstructor
public class DssModel {

    private LinkedList<String> negativeSelectedWords;
    private LinkedList<String> positiveSelectedWords;
    private int negativeWordCount;
    private int positiveWordCount;
    private double negativePercent;
    private double positivePercent;


}
