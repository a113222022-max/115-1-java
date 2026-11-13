public class MissionStep {
    private int command;
    private int energy;
    private String result;

    public MissionStep(int command, int energy, String result) {
        // TODO 1
        this.command = command;
        this.energy = energy;
        this.result = result;
    }

    // TODO 2
    public int getCommand() {
        return this.command;
    }

    public int getEnergy() {
        return this.energy;
    }

    public String getResult() {
        return this.result;
    }

    public boolean isSuccessful() {
        // TODO 3：字串要用 equals 做內容比較，不能用 ==
        return "OK".equals(this.result);
    }
}