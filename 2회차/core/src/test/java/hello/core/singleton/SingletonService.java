package hello.core.singleton;

public class SingletonService {

//    자기 자신의 객체를 생성한다.
//    여기서 private 접근 제한자와 static 을 선언해줘 객체를 추가로 생성하지 않으며,
//    static을 선언해주었기 때문에 클래스가 처음으로 로드될때 객체를 생성하고 정적 영역에 해당 객체를 등록한다.
//    특정 메소드를 통해서만 접근할 수 있도록 설정해준다.
    private static final SingletonService instance = new SingletonService();

//    싱글톤으로 설정된 객체인 자기 자신의 instance를 호출할 수 있는 getInstance
    public static SingletonService getInstance() {
        return instance;
    }

//    생성자로 외부에서의 객체 생성을 막아준다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
