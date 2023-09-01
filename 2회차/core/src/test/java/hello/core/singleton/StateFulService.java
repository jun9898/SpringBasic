package hello.core.singleton;

public class StateFulService {

//    private int price;

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기서 문제가 발생한다.
        return price;
    }

    public void getPrice() {
//        return price;
    }
}
