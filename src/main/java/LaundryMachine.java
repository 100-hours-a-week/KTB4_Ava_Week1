public class LaundryMachine extends Machine{
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
