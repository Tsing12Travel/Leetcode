package leetCode75;

import java.util.ArrayList;
import java.util.List;

public class No1431_KidsWithCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res = new ArrayList<>(candies.length);
        int premax = 0;

        for (int candy : candies) {
            premax = Math.max(premax, candy);
        }

        for (int candy : candies) {
            if (candy + extraCandies < premax) {
                res.add(false);
            } else {
                res.add(true);
            }
        }

        return res;
    }


    public List<Boolean> kidsWithCandies2(int[] candies, int extraCandies) {
        int premax = 0;
        for (int candy : candies) {
            premax = Math.max(premax, candy);
        }

        List<Boolean> res = new ArrayList<>(candies.length);
        for (int candy : candies) {
            res.add(candy + extraCandies >= premax);
        }

        return res;
    }


    public static void main(String[] args) {
        int[] candies = new int[]{2, 3, 5, 1, 3};
        No1431_KidsWithCandies No1431 = new No1431_KidsWithCandies();
        System.out.println(No1431.kidsWithCandies2(candies, 3));
    }
}
