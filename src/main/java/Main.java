import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int select; // 선택한 서비스 번호
        boolean retryPay; // 결제 실패 여부

        System.out.println("이용하실 서비스를 선택해주세요");
        System.out.println("1. 세탁기 2. 건조기 | 그 외. 선택 취소");
        System.out.print("입력: ");
        select = sc.nextInt();

        switch (select) {
            case 1: // 세탁기
                Washer washer = new Washer();
                washer.configure();
                washer.calculateRemainingTime(); // 남은 시간 계산
                washer.calculatePrice(); // 요금 계산
                do {
                    retryPay = washer.pay(); // 결제 로직(실패시 재시도)
                } while (retryPay);
                washer.run(); // 세탁기 실행
                break;
            case 2: // 건조기
                Dryer dryer = new Dryer();
                dryer.configure();
                dryer.calculateRemainingTime(); // 남은 시간 계산
                dryer.calculatePrice(); // 요금 계산
                do {
                    retryPay = !dryer.pay(); // 결제 로직(실패시 재시도)
                } while (retryPay);
                dryer.run(); // 건조기 실행
                break;
            default: // 취소
                break;
        }
    }
}

