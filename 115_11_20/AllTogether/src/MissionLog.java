import java.util.Arrays;

// 本檔已完成：固定腳本入口與報表。保留第五週 Codex 的完整輸出格式。
public class MissionLog {
    public static void main(String[] args) {
        int[] script = {1, 1, 2};
        runMission(script, 20, 0);
    }

    // 教師固定狀態測試入口，沿用第五週的簽名；不列學生 TODO。
    static void runMission(int[] script, int energy, int samples) {
        System.out.println("=== 探測車任務 ===");
        Rover rover = new Rover(energy, samples);
        Mission mission = new Mission(rover);
        mission.run(script);
        printReplay(mission);
        System.out.println("指令次數[0..3]=" + Arrays.toString(mission.countCommands()));
        printSummary(script.length, mission);
        System.out.println("最終能源=" + rover.getEnergy() + ", 樣本數=" + rover.getSamples()
                + ", 行動次數=" + rover.getActionCount() + " -> " + getOutcomeLabel(mission));
        System.out.println("電量百分比=" + rover.getEnergy() / 20.0 * 100);

        System.out.println("\n=== 重型探測車任務 ===");
        HeavyRover heavyRover = new HeavyRover(energy, samples);
        Mission heavyMission = new Mission(heavyRover);
        heavyMission.run(script);
        printReplay(heavyMission);
        System.out.println("指令次數[0..3]=" + Arrays.toString(heavyMission.countCommands()));
        printSummary(script.length, heavyMission);
        System.out.println("最終能源=" + heavyRover.getEnergy() + ", 樣本數=" + heavyRover.getSamples()
                + ", 行動次數=" + heavyRover.getActionCount() + " -> " + getOutcomeLabel(heavyMission));
        System.out.println("電量百分比=" + heavyRover.getEnergy() / 20.0 * 100);
    }

    static void printReplay(Mission mission) {
        System.out.println("=== 任務回放 ===");
        for (int i = 0; i < mission.getStepCount(); i++) {
            MissionStep step = mission.getStep(i);
            System.out.println("步驟 " + (i + 1) + ": 指令=" + step.getCommand()
                    + ", 結果=" + getResultLabel(step.getResult()) + ", 能源=" + step.getEnergy());
        }
    }

    static void printSummary(int scriptLength, Mission mission) {
        System.out.println("步驟=" + mission.getStepCount() + ", 成功=" + mission.countOk()
                + ", 拒絕=" + mission.countRejected()
                + ", 略過=" + (scriptLength - mission.getStepCount()));
        if (mission.getStepCount() == 0) {
            System.out.println("最低能源=N/A");
        } else {
            System.out.println("最低能源=" + mission.lowestEnergy());
        }
    }

    private static String getResultLabel(String result) {
        if ("OK".equals(result)) { return "成功"; }
        if ("RETURN".equals(result)) { return "返回"; }
        return result;
    }

    private static String getOutcomeLabel(Mission mission) {
        if ("SUCCESS".equals(mission.getOutcome())) { return "任務成功"; }
        if ("EXHAUSTED".equals(mission.getOutcome())) { return "能源耗盡"; }
        if ("RETURNED".equals(mission.getOutcome())) { return "已返回"; }
        return "進行中";
    }
}
