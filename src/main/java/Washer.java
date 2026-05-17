public class Washer extends LaundryMachine {
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