package just.fo.fun.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
public class UserCommentaryVoteHistory extends SuperEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Commentary commentary;

    private Boolean isUpVoted;
    private Boolean isDownVoted;

}
