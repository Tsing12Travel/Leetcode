package helloAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetSumII {
    void backtrace(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }

        for (int i = start; i < choices.length; i++) {
            if (target - choices[i] < 0) {
                break;
            }

            if (i > start && choices[i] == choices[i - 1]) {
                continue;
            }

            state.add(choices[i]);
            backtrace(state, target - choices[i], choices, i + 1, res);
            state.remove(state.size() - 1);
        }
    }

    List<List<Integer>> subsetSumII(int[] nums, int target) {
        List<Integer> state = new ArrayList<>();
        Arrays.sort(nums);
        int start = 0;
        List<List<Integer>> res = new ArrayList<>();
        backtrace(state, target, nums, start, res);
        return res;
    }
}
