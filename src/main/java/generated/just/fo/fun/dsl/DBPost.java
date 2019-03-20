package generated.just.fo.fun.dsl;

import javax.annotation.Generated;

/**
 * DBPost is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class DBPost {

    public DBPost() {
    }

    public DBPost(Long categoryId, Long id, String imageUrl, Byte isDeleted, Long rating, String title, java.sql.Timestamp updated, Long userId) {
        this.categoryId = categoryId;
        this.id = id;
        this.imageUrl = imageUrl;
        this.isDeleted = isDeleted;
        this.rating = rating;
        this.title = title;
        this.updated = updated;
        this.userId = userId;
    }

    private Long categoryId;

    private Long id;

    private String imageUrl;

    private Byte isDeleted;

    private Long rating;

    private String title;

    private java.sql.Timestamp updated;

    private Long userId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
         return "categoryId = " + categoryId + ", id = " + id + ", imageUrl = " + imageUrl + ", isDeleted = " + isDeleted + ", rating = " + rating + ", title = " + title + ", updated = " + updated + ", userId = " + userId;
    }

}

