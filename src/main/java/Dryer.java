public class Dryer extends LaundryMachine {
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
