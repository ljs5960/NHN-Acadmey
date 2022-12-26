package com.nhnacademy.exchange;

public class ExchangeService{
    private final int EXCHANGE_RATE = 1000;

    public void exchange(Money money){
        if(money.getCurrency().equals("dollar")){
            money.setCurrency("won");
            money.setAmount(money.getAmount() * EXCHANGE_RATE);
            this.roundDollarToWon(money);
        }else if(money.getCurrency().equals("won")){
            money.setCurrency("dollar");
            money.setAmount(money.getAmount() / EXCHANGE_RATE);
            roundWonToDollar(money);
        }
    }
    //달러 -> 원 환전 후, 일의 자리에서 반올림
    private void roundDollarToWon(Money money){
        if((money.getAmount() % 10) < 5){ // 5보다 작을경우 버림
            money.setAmount((int)(money.getAmount()) / 10 * 10);
        }else{
            money.setAmount(((int) (money.getAmount()) / 10) * 10 + 10);
        }
    }

    //원 -> 달러 환전 후, 소수점 셋째자리에서 반올림
    private void roundWonToDollar(Money money){
        money.setAmount(Double.parseDouble(String.format("%.2f", money.getAmount())));
    }
}
