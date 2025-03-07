import java.util.*;

public class App {
    private final ArrayList<Member> members; // 회원 정보 저장
    private Member loginMember; // 회원의 이름 저장 (작성자 체크를 위해)

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

            if (!isJoin(loginId)) { // 아이디 중복 검사
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

            if (!loginPw.equals(loginPwCheck)){ // 비밀번호 검사
                System.out.println("비밀번호를 확인해주세요.");
                continue;
            }
            break;
        }

        System.out.print("이름: ");
        String name = sc.nextLine();

        Member member = new Member(id, loginId, loginPw, name); // 전부 작성하면 회원 저장
        members.add(member);

        System.out.println(id+"번 회원이 생성되었습니다.");
        System.out.println("==== Login / Join ===="); // 다시 로그인 시도
    }

    private boolean login(Scanner sc){
        System.out.print("아이디: ");
        String loginId = sc.nextLine();

        System.out.print("비밀번호: ");
        String loginPw = sc.nextLine();

        for (Member member : members){ // 회원 리스트를 순회하며 저장된 id와 pw가 같으면 true 반환
            if (member.loginId.equals(loginId) && member.loginPw.equals(loginPw)){
                loginMember = member; // 작성자를 위한 이름 저장
                return true;
            }
        }
        return false;
    }

    private boolean isJoin(String loginId){ // 이미 회원가입을 하였는지 체크
        int index = getMember(loginId); 
        if (index == -1){ // 중복이 아니라면 true
            return true;
        }
        return false;
    }

    private int getMember(String loginId){
        int i = 0;
        for (Member member : members){ // 회원 리스트를 순회하여 같은 id가 있는지 체크 
            if (member.loginId.equals(loginId)){
                return i; // 같은 아이디가 있다면 i를 반환, isJoin에서 false를 반환하게하여 중복 생성을 막음
            }
        }
        return -1;
    }
}
