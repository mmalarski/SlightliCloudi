package mm.mr.kw.hk;

public class App {
    public static void main(String[] args) {
        final User demouser = User.builder()
                .mail("demouser@demo.com")
                .username("name")
                .address("ul. Cupertino 123")
                .country("USA").build();

        System.out.println(demouser);
    }
}
