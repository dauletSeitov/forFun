package just.fo.fun.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteData {


    private Boolean userActionIsUpVote;

    private Boolean dbIsUpVoted;
    private Boolean dbIsDownVoted;


}
