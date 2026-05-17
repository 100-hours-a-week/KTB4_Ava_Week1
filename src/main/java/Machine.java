public class Machine {
    protected int remainingTime; // 남은 시간
    private int price; // 요금
    private int money; // 지불 금액

    // 요금 계산 메소드
    public void calculatePrice() {
        price += remainingTime * 100;
    }

    // 요금 지불 메소드
    public boolean pay() {
        System.out.println("총 요금은 " + price + "원");
        System.out.print("결제하실 금액을 입력해주세요: ");
        money = Main.sc.nextInt();
        if (price == money) { // 요금만큼 지불
            return false;
        } else if (price < money) { // 요금보다 많이 지불
            System.out.println("잔돈 " + (money - price) + "원을 반환합니다.");
            return false;
        } else { // 요금보다 적게 지불 (재시도 반환값 리턴)
            price -= money;
            System.out.println(price + "원 부족합니다.");
            return true;
        }
    }

    // 남은 시간 출력 메소드
    public void showRemainingTime() {
        System.out.println("남은 시간: " + remainingTime + "분");
    }
}
