import java.util.*;

public class App {
    private final ArrayList<Member> members;
    private Member loginMember;

    public App(){
        members = new ArrayList<>();
        loginMember = null;
    }

    public String start(){
        System.out.println("==== Login / Join ====");
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.print("login / join / exit: ");
            String command = sc.nextLine();

            if (command.equals("exit")) return null;

            else if (command.equals("join")){
                join(sc);
            } else if (command.equals("login")){
                if (login(sc)){
                    System.out.println("로그인 성공!");
                    return loginMember.name;
                } else{
                    System.out.println("로그인 실패!");
                }
            }
        }
    }

    private void join(Scanner sc){
        int id = members.size() + 1;

        String loginId;
        while (true){
            System.out.print("아이디: ");
            loginId = sc.nextLine();

            if (!isJoin(loginId)) {
                System.out.println("이미 사용중인 아이디입니다.");
                continue;
            }
            break;
        }

        String loginPw, loginPwCheck;
        while (true){
            System.out.print("비밀번호: ");
            loginPw = sc.nextLine();
            System.out.print("비밀번호 확인: ");
            loginPwCheck = sc.nextLine();

            if (!loginPw.equals(loginPwCheck)){
                System.out.println("비밀번호를 확인해주세요.");
                continue;
            }
            break;
        }

        System.out.print("이름: ");
        String name = sc.nextLine();

        Member member = new Member(id, loginId, loginPw, name);
        members.add(member);

        System.out.println(id+"번 회원이 생성되었습니다.");
        System.out.println("==== Login / Join ====");
    }

    private boolean login(Scanner sc){
        System.out.print("아이디: ");
        String loginId = sc.nextLine();

        System.out.print("비밀번호: ");
        String loginPw = sc.nextLine();

        for (Member member : members){
            if (member.loginId.equals(loginId) && member.loginPw.equals(loginPw)){
                loginMember = member;
                return true;
            }
        }
        return false;
    }

    private boolean isJoin(String loginId){
        int index = getMember(loginId);
        if (index == -1){
            return true;
        }
        return false;
    }

    private int getMember(String loginId){
        int i = 0;
        for (Member member : members){
            if (member.loginId.equals(loginId)){
                return i;
            }
        }
        return -1;
    }
}
