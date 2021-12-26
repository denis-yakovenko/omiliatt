package omilia;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static omilia.Constants.*;

public class NaturalNumbersInterpretation {

    public static void main(String[] args) {
        if (args.length == 0) {
            log("Input number is missing");
            return;
        }
        String input = String.join(SEPARATOR, args);
        baseLevelValidation(input);
        advancedLevelValidation(input);
    }

    static boolean isNumberValid(String in) {
        if (in.length() == 10) {
            return in.startsWith(VALID_10_DIGIT_PREFIXES[0])
                    || in.startsWith(VALID_10_DIGIT_PREFIXES[1]);
        } else if (in.length() == 14) {
            return in.startsWith(VALID_14_DIGIT_PREFIX + VALID_10_DIGIT_PREFIXES[0])
                    || in.startsWith(VALID_14_DIGIT_PREFIX + VALID_10_DIGIT_PREFIXES[1]);
        }
        return false;
    }

    static void baseLevelValidation(String in) {
        log("*** Base Level Validation ***");
        boolean valid;
        if (in == null || in.isEmpty()) return;
        String cleared = in.replace(SEPARATOR, EMPTY);
        valid = isNumberValid(cleared);
        log("input: " + in + ", output: " + cleared + " [phone number: " + (valid ? V.VALID : V.INVALID) + "]");
    }

    static void advancedLevelValidation(String in) {
        log("*** Advanced Level Validation ***");
        String[] interpretations = interpret(in);
        for (int i = 0; i < interpretations.length; i++) {
            String interpretation = interpretations[i];
            boolean valid = isNumberValid(interpretation);
            log("Interpretation " + i + ": " + interpretation + " [phone number: " + (valid ? V.VALID : V.INVALID) + "]");
        }
    }

    static String[] interpret(String in) {
        String numbers = Arrays
                .stream(in.split(SEPARATOR))
                .map(NaturalNumbersInterpretation::expandNumbers)
                .collect(Collectors.joining(SEPARATOR));
        return identifyNaturalNumberAmbiguities(numbers);
    }

    private static String[] identifyNaturalNumberAmbiguities(String in) {
        String[] numbers = in.split(SEPARATOR);
        ArrayList<Integer[]> out = new ArrayList<>();
        Integer elemPrev;
        Integer digitsPrev = null;
        ArrayList<Integer> stS = new ArrayList<>();
        for (String elemCurr : numbers) {
            int digitsCurr = elemCurr.length();
            if (digitsPrev != null && digitsPrev <= digitsCurr) {
                out.add(listItoArray(stS));
                stS.clear();
            }
            stS.add(Integer.valueOf(elemCurr));
            elemPrev = Integer.valueOf(elemCurr);
            digitsPrev = String.valueOf(elemPrev).length();
        }
        out.add(listItoArray(stS));
        stS.clear();
        List<String[]> possibleOptions = out
                .stream()
                .map(NaturalNumbersInterpretation::getPossibleOptions)
                .collect(Collectors.toList());
        List<String> result = generateAllPossiblePhoneNumbers(EMPTY, possibleOptions);
        return result.toArray(new String[0]);
    }

    static List<String> generateAllPossiblePhoneNumbers(String prefix, List<String[]> in) {
        List<String> result = new ArrayList<>();
        String[] a = in.get(0);
        for (String s : a) {
            if (in.size() > 1) {
                List<String[]> b = in.subList(1, in.size());
                result.addAll(generateAllPossiblePhoneNumbers(prefix + s, b));
            } else
                result.add(prefix + s);
        }
        return result;
    }

    static String[] getPossibleOptions(Integer[] in) {
        if (in.length == 3) {
            return new String[]{
                    String.valueOf(in[0] + in[1] + in[2]),
                    String.valueOf(in[0] + in[1]) + String.valueOf(in[2]),
                    String.valueOf(in[0]) + String.valueOf(in[1] + in[2]),
                    String.valueOf(in[0]) + String.valueOf(in[1]) + String.valueOf(in[2])
            };
        } else if (in.length == 2) {
            return new String[]{
                    String.valueOf(in[0] + in[1]),
                    String.valueOf(in[0]) + String.valueOf(in[1])
            };
        } else if (in.length == 1) {
            return new String[]{
                    String.valueOf(in[0])
            };
        } else return new String[0];
    }

    static String expandNumbers(String in) {
        ArrayList<String> out = new ArrayList<>();
        if (in.equals("0")) {
            out.add("0");
        } else
            for (int i = 0; i < in.length(); i++) {
                int value = Integer.parseInt(in.substring(i));
                if (value <= 20) {
                    if (value > 0) {
                        out.add(String.valueOf(value));
                    }
                    break;
                } else {
                    int power = (int) Math.pow(10.0, in.length() - 1.0 - i);
                    int b = value / power;
                    out.add(String.valueOf(b * power));
                }
            }
        return String.join(SEPARATOR, out);
    }

    static Integer[] listItoArray(List<Integer> in) {
        return in.toArray(new Integer[0]);
    }

    static void log(Object o) {
        if (o != null) System.out.println(o);
    }
}
