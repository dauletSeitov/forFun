package just.fo.fun.dss.twitter;

import lombok.Data;

@Data
public class TwitterDataItem {

    private String text;
    private TwitterDataUser user;

    private /*LocalDateTime*/String created_at;

}
