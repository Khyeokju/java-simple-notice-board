import java.util.*;

public class Main {
    private final ArrayList<Article> articles = new ArrayList<>();
    private int articleCount = 0; // 멤버 변수로 생성
    private String UserName;

    public static void main(String[] args) {
        Main main = new Main(); 
        main.run();
    //객체를 따로 생성하고 run()에서 실행하는 이유
    //객체지향적인 코드 유지 (static 사용 최소화)
    //main()을 가볍게 유지하여 프로그램 실행 흐름을 명확하게 분리
    //확장성 증가 (여러 개의 Main 객체를 독립적으로 실행 가능)
    //디버깅 및 유지보수 용이 (로직이 run()에 집중됨)
    // 객체지향 원칙을 따르고, 유지보수와 확장성이 좋은 구조를 만들기 위해 이런 방식이 권장됨
    }

    public void run() {
        App app = new App();
        String username = app.start(); // app 메서드를 실행하여 로그인한 username 저장

        if (username == null) {
            System.out.println("로그인 실패 프로그램 종료");
            return;
        }
        UserName = username;

        System.out.println("======= 게시판 =======");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("write / list [string] / detail [num] / delete [num] / modify [num] / exit : ");
            String commend = sc.nextLine();

            if (commend.equals("exit")) {
                break;
            } else if (commend.equals("write")) {
                write(sc);
            } else if (commend.startsWith("list")) {
                list(commend);
            } else if (commend.startsWith("detail ")) {
                detail(commend);
            } else if (commend.startsWith("delete ")) {
                delete(commend);
            } else if (commend.startsWith("modify ")) {
                modify(commend, sc);
            } else {
                System.out.println("잘못된 명령어입니다.");
            }
        }
        sc.close();
        System.out.println("======게시판 종료======");
        }

        private void write (Scanner sc) { 
            int id = articleCount + 1;
            articleCount = id;

            System.out.print("제목 : ");
            String title = sc.nextLine();
            System.out.print("내용 : ");
            String body = sc.nextLine();
            int hit = 0;
            String nowDate = Util.getNowDateStr(); // Util 클래스로 작성 시간 저장
            Article article = new Article(id, title, body, hit, nowDate, UserName); // 게시글 생성
            articles.add(article); // 게시글 저장

            System.out.println(id + "번째 글이 생성되었습니다.");
        }

        private void list(String commend) {
            if (articles.isEmpty()) {
                System.out.println("게시물이 없습니다.");
                return;
            }
            String Keyword = commend.substring(4).trim(); // ex) list 2 에서 list와 공백을 제거하여 2만 남김
            System.out.println("검색어: " + Keyword);
            ArrayList<Article> searchArticle = articles; // 찾으려는 게시글을 저장할 배열 생성

            if (!Keyword.isEmpty()) {
                searchArticle = new ArrayList<>();

                for (Article article : articles) {
                    if (article.title.contains(Keyword)) {
                        searchArticle.add(article);
                    }
                }
            }
            if (searchArticle.isEmpty()) {
                System.out.println("검색어와 일치한 게시물이 존재하지 않습니다.");
                return;
            }
            for (int i = searchArticle.size() - 1; i >= 0; i--) {
                Article article = searchArticle.get(i);
                System.out.println("id : title");
                System.out.println(article.id + " : " + article.title);
            }
        }

        private void detail (String commend) {
            String[] detailNum = commend.split(" ");
            int id = Integer.parseInt(detailNum[1]); // 조회하고자 하는 게시글 번호 저장

            Article foundArticle = null; // 찾으려는 게시글을 저장할 객체
            // foundArticle를 통해 객체 참조

            for (int i = 0; i < articles.size(); i++) {
                Article article = articles.get(i);
                if (article.id == id) { // 번호와 일치하면 저장
                    foundArticle = article;
                    break;
                }
            }
            if (foundArticle == null) {
                System.out.println(id + "번 게시물은 존재하지 않습니다.");
                return;
            }

            foundArticle.uphit();
            System.out.println("==== Detail ====");
            System.out.println("번호: " + foundArticle.id);
            System.out.println("제목: " + foundArticle.title);
            System.out.println("내용: " + foundArticle.body);
            System.out.println("조회: " + foundArticle.hit);
            System.out.println("날짜: " + foundArticle.nowDate);
            System.out.println("작성자: " + foundArticle.author);
            System.out.println("================");
        }

        private void delete (String commend){
            String[] deleteNum = commend.split(" ");
            int id = Integer.parseInt(deleteNum[1]);
            int foundIndex = -1;

            for (int i = 0; i < articles.size(); i++) {
                Article article = articles.get(i);
                if (article.id == id) {
                    foundIndex = i;
                    break;
                }
            }
            if (foundIndex == -1) {
                System.out.println(id + "번째 게시물이 존재하지 않습니다.");
                return;
            }
            articles.remove(foundIndex);
            System.out.println(id + "번째 게시물이 삭제되었습니다.");
        }

        private void modify(String commend, Scanner sc){
            String[] commendBits = commend.split(" ");
            int id = Integer.parseInt(commendBits[1]);
            Article foundArticle = null;

            for (int i = 0; i < articles.size(); i++) {
                Article article = articles.get(i);
                if (article.id == id) {
                    foundArticle = article;
                    break;
                }
            }
            if (foundArticle == null) {
                System.out.println("없는 게시물 입니다.");
                return;
            }
            System.out.print("수정 제목: ");
            String title = sc.nextLine();
            System.out.print("수정 내용: ");
            String body = sc.nextLine();

            foundArticle.title = title;
            foundArticle.body = body;
            System.out.println(foundArticle.id + "번 게시물 수정 완료");
        }
}
