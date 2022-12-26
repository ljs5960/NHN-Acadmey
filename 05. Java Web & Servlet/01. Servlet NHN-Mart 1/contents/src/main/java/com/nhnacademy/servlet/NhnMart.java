package com.nhnacademy.servlet;

import java.io.IOException;
import java.util.Scanner;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class NhnMartShell extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init() called");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.getWriter().println("hello world");
    }

    public static void main(String[] args) {
        NhnMart mart = new NhnMart();
        mart.prepareMart();

        BuyList buyList = inputBuyListFromShell();

        Customer jordan = new Customer(buyList);
        // 장바구니를 챙긴다.
        jordan.bring(mart.provideBasket());
        // 식품을 담는다.
        jordan.pickFoods(mart.getFoodStand());
        // 카운터에서 계산한다.
        jordan.payTox(jordan.getBasket());
    }

    private static BuyList inputBuyListFromShell() {
        // TODO
        // Scanner에서 buyList 만들기
        // BuyList buyList = new BuyList();
        // buyList.add(new BuyList.Item("양파", 2));
        // buyList.add(new BuyList.Item("계란", 3));
        System.out.println("NHN 마트에 오신 것을 환영합니다. 사고 싶은 물건을 골라주세요.");
        Scanner scanner = new Scanner(System.in);
        String[] inputItems = scanner.nextLine().split(" ");

        BuyList buyList = new BuyList();
        for (int i = 0; i < inputItems.length; i++) {
            if (i % 2 == 0) {
                try {
                    buyList.add(new BuyList.Item(inputItems[i], Integer.parseInt(inputItems[i + 1])));
                } catch (Exception e) { // 상품명+갯수 입력 format 불일치 예외 처리
                    System.out.println("명령을 정확히 입력해주세요.");
                }
            }
        }

        return buyList;
    }
}

public class NhnMart extends HttpServlet {
    private final FoodStand foodStand = new FoodStand();

    public void prepareMart() {
        fillFoodStand();
    }

    private void fillFoodStand() {
        for (int i = 0; i < 2; i++) {
            foodStand.add(new Food("양파", 1_000));
        }
        for (int i = 0; i < 5; i++) {
            foodStand.add(new Food("계란(30개)", 5_000));
        }
        for (int i = 0; i < 10; i++) {
            foodStand.add(new Food("파", 500));
        }
        for (int i = 0; i < 20; i++) {
            foodStand.add(new Food("사과", 2_000));
        }
    }

    public Basket provideBasket() {
        return new Basket();
    }

    public FoodStand getFoodStand() {
        return this.foodStand;
    }
}
