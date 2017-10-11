package lab07.student58070023.lazyinstagram.model;

import java.util.List;

/**
 * Created by Beedle on 11/10/2560.
 */

public class UserPosts {
    public UserPosts() {
    }

    private int like;
    private int comment;
    private String url;

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
