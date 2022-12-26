import java.math.BigDecimal;

public class Atm {
    public boolean deposit(BigDecimal amount, Long accountNumber) {
        System.out.println("[Atm] Amount: " + amount + ", AccountNumber: " + accountNumber);
        return new RestApi().postAmount(Action.DEPOSIT, accountNumber, amount);
    }

    public boolean withDraw(BigDecimal amount, Long accountNumber) {
        System.out.println("Withdraw: " + accountNumber);
        return new RestApi().postAmount(Action.WITHDRAW, accountNumber, amount);
    }
}
