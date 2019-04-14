package domain;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        int MAX_NAME_LENGTH = 5;

        if (name.length() > MAX_NAME_LENGTH) {
            System.out.println("자동차 이름은 최대 5글자로 입력해주세요.");
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return  position;
    }

    public boolean tryRun() {
        int GO_STOP_STANDARD = 4;
        int decidingNumber = (int)(Math.random() * 9 + 0);
        boolean isRun = false;

        if (decidingNumber >= GO_STOP_STANDARD) {
            isRun = true;
        }

        return isRun;
    }

    public void showTrace(boolean isRun) {
        if (isRun) {
            position = position + 1;
        }
        String progress = new String(new char[position]).replace("\0", "-");
        System.out.println(String.format("%s : %s", name, progress));
    }

    public boolean isMaxPosition(int maxPosition) {
        if (position == maxPosition) {
            return true;
        }
        return false;
    }
}
