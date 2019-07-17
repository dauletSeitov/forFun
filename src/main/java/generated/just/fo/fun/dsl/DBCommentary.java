package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBCommentary is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBCommentary {

    public DBCommentary() {
    }

    public DBCommentary(Long id, String imageUrl, Byte isDeleted, Long parentId, Long postId, Long rating, String text, java.sql.Timestamp updated, Long userId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.isDeleted = isDeleted;
        this.parentId = parentId;
        this.postId = postId;
        this.rating = rating;
        this.text = text;
        this.updated = updated;
        this.userId = userId;
    }

    private Long id;

    private String imageUrl;

    private Byte isDeleted;

    private Long parentId;

    private Long postId;

    private Long rating;

    private String text;

    private java.sql.Timestamp updated;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public java.sql.Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(java.sql.Timestamp updated) {
        this.updated = updated;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
         return "id = " + id + ", imageUrl = " + imageUrl + ", isDeleted = " + isDeleted + ", parentId = " + parentId + ", postId = " + postId + ", first = " + rating + ", text = " + text + ", updated = " + updated + ", userId = " + userId;
    }

}

