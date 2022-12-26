import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        var atm = new Atm();
        System.out.println("[Application] " + atm.deposit(new BigDecimal(100), 123L));
    }
}
