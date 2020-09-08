import game.process.GameProcess;
import org.junit.Assert;

public class Test {
    @org.junit.Test
    public void test() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d";
        String output = "Ac4d=Ad4s 5d6d As9s KhKd";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }

    @org.junit.Test
    public void test2() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "2h3h4h5d8d KdKs 9hJh";
        String output = "KdKs 9hJh";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }

    @org.junit.Test
    public void testRepeating1v1() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "4cKs4h8s7s AdTs 8c7d AsAs KhKd 7d6d";
        String output = "AdTs 7d6d 8c7d AsAs KhKd";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }

    @org.junit.Test
    public void testRepeating1v2() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "4h8s7s4cKs AsAs KhKd 7d6d AdTs 8c7d";
        String output = "AdTs 7d6d 8c7d AsAs KhKd";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }

    @org.junit.Test
    public void testRepeating2v1() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "4c8sKs4h7s Kd4s Ac4d";
        String output = "Ac4d Kd4s";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }

    @org.junit.Test
    public void testRepeating2v2() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "4cKs4h8s7s Kd4s Ac4d";
        String output = "Ac4d Kd4s";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }

    @org.junit.Test
    public void testRepeating2v3() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "4cKsTh8s7s Kd4s Tc4d Kd8d 7d4d 4dTd";
        String output = "7d4d 4dTd=Tc4d Kd4s Kd8d";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }

    @org.junit.Test
    public void testRepeating3() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "4cKs4h8s7s Kd7s AcAd";
        String output = "Kd7s AcAd";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }

    @org.junit.Test
    public void testRepeatingFullHouse1() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "4cKs4h7s7s Kd4s 7c4d 7cKd";
        String output = "Kd4s 7c4d 7cKd";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }

    @org.junit.Test
    public void testRepeatingSuit1() throws Exception {
        GameProcess gameProcess = new GameProcess();

        String input = "4cKs4h7s7s As5s Ts2s 5cKd";
        String output = "Kd4s 7c4d 7cKd";

        String result = gameProcess.main(input);
        Assert.assertEquals(output, result);
    }
}
