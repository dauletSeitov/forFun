package just.fo.fun.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
public class UserPostVoteHistory extends SuperEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private Boolean isUpVoted = false;
    private Boolean isDownVoted = false;

}
