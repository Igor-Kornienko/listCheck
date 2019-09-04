package main.java;

import java.util.*;

public class Main {
    public static void main(String... args) {

        Double[][] res = new Double[2][4];
        String[] names = {"Sort:          ", "End insert:    ", "Center insert: ", "Search:        "};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                res[j][i] = 0.0;
            }
            System.out.println();
        }

        int tryCount = 100;

        for (int k = 0; k < tryCount; k++) {

            List<List<Integer>> lists = new ArrayList<>();

            lists.add(new ArrayList<>());
            lists.add(new LinkedList<>());

            Random random = new Random();

            for (int i = 0; i < 1000000; i++) {
                int num = random.nextInt();

                for (List a : lists) {
                    a.add(num);
                }
            }

            Date startTime;

            //Sort
            for (int i = 0; i < lists.size(); i++) {
                startTime = new Date();
                lists.get(i).sort(new Sort());
                res[i][0] += (new Date()).getTime() - startTime.getTime() * 1.0;
            }

            //search
            for (int i = 0; i < lists.size(); i++) {
                startTime = new Date();
                for (int j = 0; j < 1000; j++) {
                    lists.get(i).indexOf(lists.get(i).get(j * 100));
                }
                res[i][3] += (new Date()).getTime() - startTime.getTime();
            }

            //insert into end
            for (int i = 0; i < lists.size(); i++) {
                startTime = new Date();
                for (int j = 0; j < 1000; j++) {
                    lists.get(i).add(random.nextInt());
                }
                res[i][1] += (new Date()).getTime() - startTime.getTime();
            }

            //insert into center
            for (int i = 0; i < lists.size(); i++) {
                startTime = new Date();
                for (int j = 0; j < 1000; j++) {
                    lists.get(i).add(lists.size() / 2, random.nextInt());
                }
                res[i][2] += (new Date()).getTime() - startTime.getTime();
            }
        }

        System.out.println("               Array    Linked");
        for (int i = 0; i < 4; i++) {
            System.out.print(names[i]);
            for (int j = 0; j < 2; j++) {
                System.out.print(res[j][i] / tryCount + "    ");
            }
            System.out.println();
        }
    }
}

class Sort implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        if (a < b)
            return -1;
        else if (a >= b)
            return 1;
        else
            return 0;
    }
}

