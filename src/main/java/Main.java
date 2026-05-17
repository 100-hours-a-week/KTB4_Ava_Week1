import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int select = 0; // 선택한 서비스 번호
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

class Machine {
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

class LaundryMachine extends Machine {
    // 횟수 입력 메소드
    public int inputCount(String type) {
        System.out.print(type + " 횟수를 입력해주세요: ");
        return Main.sc.nextInt();
    }

    // 기계 동작 메소드 (횟수 당 1초 대기)
    public void operate(String type, int count, int time) {
        while (count != 0) {
            System.out.println(type + "중 - " + (count - 1) + "회 남음");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            remainingTime -= time;
            count -= 1;
        }
    }
}

class Washer extends LaundryMachine {
    private int washCount; // 세탁 횟수
    private int rinseCount; // 헹굼 횟수
    private int spinCount; // 탈수 횟수

    // 각 횟수 설정
    public void configure() {
        washCount = inputCount("세탁");
        rinseCount = inputCount("헹굼");
        spinCount = inputCount("탈수");
    }

    // 남은 시간 계산 메소드
    public void calculateRemainingTime() {
        remainingTime = washCount * 10 + rinseCount * 5 + spinCount * 3;
    }

    // 린스
    private void wash() {
        showRemainingTime();
        operate("세탁", washCount, 10);
    }

    // 헹굼
    private void rinse() {
        showRemainingTime();
        operate("헹굼", rinseCount, 5);
    }

    // 탈수
    private void spin() {
        showRemainingTime();
        operate("탈수", spinCount, 3);
    }

    // 실행 메소드
    public void run() {
        System.out.println("세탁기 이용을 시작합니다.");
        wash();
        rinse();
        spin();
        System.out.println("세탁기 이용이 종료되었습니다.");
    }
}

class Dryer extends LaundryMachine {
    private int dryCount; // 건조 횟수
    private int temperature; // 건조 온도

    Dryer() {
        temperature = 0;
    }

    // 횟수, 온도 설정
    public void configure() {
        dryCount = inputCount("건조");
        while (temperature >= 4 || temperature <= 0) {
            System.out.println("건조 온도를 선택해주세요.");
            System.out.println("1. 저온(40°C) 2. 중온(55°C) 3. 고온(70°C)");
            System.out.print("입력: ");
            temperature = Main.sc.nextInt();
            if (temperature >= 4 || temperature <= 0)
                System.out.println("1, 2, 3 중에 선택해주세요.");
        }
    }

    // 남은 시간 계산 메소드
    public void calculateRemainingTime() {
        remainingTime = dryCount * 10;
    }

    // 건조
    private void dry() {
        showRemainingTime();
        System.out.println("건조 온도 - " + (temperature == 1 ? "저온" : temperature == 2 ? "중온" : "고온"));
        operate("건조", dryCount, 10);
    }

    // 실행 메소드
    public void run() {
        System.out.println("건조기 이용을 시작합니다.");
        dry();
        System.out.println("건조기 이용이 종료되었습니다.");
    }
}