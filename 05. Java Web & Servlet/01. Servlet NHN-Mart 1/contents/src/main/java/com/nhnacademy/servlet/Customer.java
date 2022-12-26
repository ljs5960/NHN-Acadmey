package com.nhnacademy.servlet;

public class Customer {
    // 고객의 구매 목록
    private final BuyList buyList;
    // 고객의 장바구니
    private Basket basket;

    public static int BALANCE = 20000;

    public Customer(BuyList buyList) {
        this.buyList = buyList;
    }

    public Basket getBasket() {
        return basket;
    }

    // 장바구니를 챙김
    public void bring(Basket basket) {
        this.basket = basket;
    }

    // 매대에서 음식을 고름 (재고 부족시 메시지 출력)
    public void pickFoods(FoodStand foodStand) {
        for (BuyList.Item food : buyList.getItems()) {
            switch (food.getName()) {
                case "양파" :
                    if (food.getAmount() <= 2) {    // 재고부족 예외처리
                        basket.add(food);
                    } else {
                        System.out.println("재고가 부족합니다.");
                    }
                    break;

                case "계란" :
                    if (food.getAmount() <= 5) {
                        basket.add(food);
                    } else {
                        System.out.println("재고가 부족합니다.");
                    }
                    break;

                case "파" :
                    if (food.getAmount() <= 10) {
                        basket.add(food);
                    } else {
                        System.out.println("재고가 부족합니다.");
                    }
                    break;

                case "사과" :
                    if (food.getAmount() <= 20) {
                        basket.add(food);
                    } else {
                        System.out.println("재고가 부족합니다.");
                    }
                    break;

                default:
                    System.out.println("상품명을 확인해주세요.");
            }
        }
    }

    // 카운터에서 계산
    public void payTox(Basket basket) {
        int amount = 0;
        for (BuyList.Item item : basket.getFoods()) {
            if (item.getAmount() > 0) {
                switch (item.getName()) {
                    case "양파":
                        amount += 1000 * item.getAmount();
                        break;
                    case "계란":
                        amount += 2000 * item.getAmount();
                        break;
                    case "파":
                        amount += 500 * item.getAmount();
                        break;
                    case "사과":
                        amount += 2000 * item.getAmount();
                        break;
                    default:    // 상품명 오입력 예외처리
                        System.out.println("상품명을 확인해주세요.");
                }
            } else {    // 상품갯수 음수입력 예외처리
                System.out.println("상품 갯수를 확인해주세요.");
            }
        }

        if ((0 < amount) && (amount <= BALANCE)) {
            System.out.println("총 가격은 " + amount + "원 입니다.");
            System.out.println("고객님 결제 후 잔액: " + (BALANCE - amount));
        } else if (amount > BALANCE) {  // 예산초과 예외처리
            System.out.println("잔액 부족");
        }
    }
}
