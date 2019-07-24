package just.fo.fun.post.repository;

import com.querydsl.sql.SQLQueryFactory;
import generated.just.fo.fun.dsl.DBPost;
import generated.just.fo.fun.dsl.QPost;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.Objects;

import static just.fo.fun.constants.Constants.FALSE;

@Deprecated
@Repository
public class DSLPostRepository {

        @Inject
        private SQLQueryFactory queryFactory;

        private static final QPost post = QPost.post;

        public Long changeRatingById(Long postId, Boolean isUpVote) {

                DBPost dbPost = queryFactory.select(post)
                        .from(post)
                        .where(post.isDeleted.eq(FALSE)
                                .and(post.id.eq(postId)))
                        .fetchOne();

                Objects.requireNonNull(dbPost, "there no post");

                return queryFactory.update(post)
                        .set(post.rating, isUpVote ? dbPost.getRating() + 1 : dbPost.getRating() - 1)
                        .where(post.id.eq(postId))
                        .execute();

        }


//        @Autowired
//        private SQLQueryFactory queryFactory;

//QCommentary commentary = QCommentary.commentary;
//List<DBCommentary> fetch = queryFactory.select(commentary).from(commentary).fetch();
}
