package omilia;

import org.junit.Test;

import java.util.Arrays;

import static omilia.Constants.PHONE_NUMBER_21;
import static omilia.NaturalNumbersInterpretation.*;

public class NaturalNumbersInterpretationTest {

    @Test
    public void TestExpandNumbers() {
        assert "1".equals(expandNumbers("1"));
        assert "7".equals(expandNumbers("7"));
        assert "10".equals(expandNumbers("10"));
        assert "11".equals(expandNumbers("11"));
        assert "19".equals(expandNumbers("19"));
        assert "20".equals(expandNumbers("20"));
        assert "20 1".equals(expandNumbers("21"));
        assert "80 7".equals(expandNumbers("87"));
        assert "100".equals(expandNumbers("100"));
        assert "100 1".equals(expandNumbers("101"));
        assert "100 10".equals(expandNumbers("110"));
        assert "100 19".equals(expandNumbers("119"));
        assert "100 20 1".equals(expandNumbers("121"));
        assert "900 80 7".equals(expandNumbers("987"));
    }

    @Test
    public void TestGetOptions() {
        Integer[] in = {100, 10, 1};
        String[] out = {"111", "1101", "10011", "100101"};
        assert Arrays.equals(getPossibleOptions(in), out);
        in = new Integer[]{10, 1};
        out = new String[]{"11", "101"};
        assert Arrays.equals(getPossibleOptions(in), out);
        in = new Integer[]{1};
        out = new String[]{"1"};
        assert Arrays.equals(getPossibleOptions(in), out);
    }

    @Test
    public void TestInterpret() {
        assert arrayEqualsIgnoreOrder(
                new String[]{
                        "210693664",
                        "2106936604",
                        "2106930664",
                        "21069306604",
                        "2106093664",
                        "21060936604",
                        "21060930664",
                        "210609306604"}, interpret(PHONE_NUMBER_21));
    }

    static boolean arrayEqualsIgnoreOrder(Object[] a, Object[] a2) {
        return Arrays.equals(
                Arrays.stream(a).sorted().toArray(),
                Arrays.stream(a2).sorted().toArray()
        );
    }
}
