package forpdateam.ru.forpda.api.theme.models;

import java.util.ArrayList;

import forpdateam.ru.forpda.api.theme.interfaces.IThemePage;

/**
 * Created by radiationx on 04.08.16.
 */
public class ThemePage implements IThemePage {
    private String title, desc, html, url, elementToScroll;
    private boolean inFavorite = false, curator = false, quote = false;
    private ArrayList<ThemePost> posts = new ArrayList<>();
    private Poll poll;

    private int id = 0, forumId = 0;
    private int postsOnPageCount = 20, allPagesCount = 1, currentPage = 1, scrollY = 0;

    public boolean canQuote() {
        return quote;
    }

    public void setCanQuote(boolean quote) {
        this.quote = quote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCurator() {
        return curator;
    }

    public void setCurator(boolean curator) {
        this.curator = curator;
    }

    public int getScrollY() {
        return scrollY;
    }

    public void setScrollY(int scrollY) {
        this.scrollY = scrollY;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getElementToScroll() {
        return elementToScroll;
    }

    public void setElementToScroll(String elementToScroll) {
        this.elementToScroll = elementToScroll;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean isInFavorite() {
        return inFavorite;
    }

    public void setInFavorite(boolean inFavorite) {
        this.inFavorite = inFavorite;
    }

    @Override
    public int getPostsOnPageCount() {
        return postsOnPageCount;
    }

    public void setPostsOnPageCount(int postsOnPageCount) {
        this.postsOnPageCount = postsOnPageCount;
    }

    @Override
    public int getAllPagesCount() {
        return allPagesCount;
    }

    public void setAllPagesCount(int allPagesCount) {
        this.allPagesCount = allPagesCount;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public ArrayList<ThemePost> getPosts() {
        return posts;
    }

    public void addPost(ThemePost post) {
        posts.add(post);
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public int getSt() {
        return currentPage * postsOnPageCount;
    }
}
