public class Article {
    int id;
    String title;
    String body;
    int hit;
    String nowDate;
    String author;

    public Article(int id, String title, String body, int hit, String nowDate, String author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.hit = 0;
        this.nowDate = nowDate;
        this.author = author;
    }
    public void uphit(){
        hit++;
    }
}

