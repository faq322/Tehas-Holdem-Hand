import main.game.process.GameProcess;
import org.junit.Assert;

public class Test {
    @org.junit.Test
    public void test() throws Exception{

        String input = "4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d";
        String output = "Ac4d=Ad4s 5d6d As9s KhKd";

        String result = GameProcess.main(input);
        Assert.assertEquals(output,result);
    }

}
