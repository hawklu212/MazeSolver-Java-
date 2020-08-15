package com.company;

import Models.Position;
import java.util.LinkedList;

public class Main {
    static int maze_size = 20;
    static int x = 3;
    static int s = 2;
    static int maze[] = {
            0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 3,
            0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1,
            0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0,
            0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0,
            1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1,
            1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1,
            1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1,
            0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1,
            1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1,
            1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0,
            1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1,
            1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1,
            1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1,
            1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1,
            0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1,
            0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1,
            0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0,
            0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0,
            2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    };


    static LinkedList MazeSolver(int maze[], Integer index, LinkedList<Integer> l, Position ps,
                                 int curr, int fastest) {
        ////Termination Condition
        if (maze[index] == 0 || curr > fastest && fastest != 0 || curr > maze_size * maze_size / 6) {
            return null;
            ////Return found pathway
        }
        if (maze[index] == x) {
            return l;
        }
        LinkedList<Integer> smallest = null;
        ////Move up the Maze return pathway begin from root

        Integer nex1 = index - maze_size;
        if (ps.row - 1 >= 0 && !l.contains(nex1)) {
            l.add(nex1);
            Position u = new Position(ps.row - 1, ps.column);
            LinkedList<Integer> goUp = MazeSolver(maze, index - maze_size, l, u, curr + 1, fastest);
            if (goUp != null) {
//        if (root == 0) {
//            printf("\nUp Path:");
//            iterateList(goUp);
//
//        }
//        freeLL(smallest);
                smallest = (LinkedList<Integer>) goUp.clone();
                fastest = smallest.size();
            }
            l.remove(nex1);
        }
        ////Move down the Maze return pathway begin from root
        Integer nex2 = index + maze_size;
        if (ps.row + 1 < maze_size && !l.contains(nex2)) {
            l.add(nex2);
            Position d = new Position(ps.row + 1, ps.column);
            LinkedList<Integer> goDown = MazeSolver(maze, index + maze_size, l, d, curr + 1, fastest);
            if (goDown != null) {
//        if (root == 0) {
//            printf("\nDown Path:");
//            iterateList(goDown);
//
//        }
                if (smallest == null || goDown.size() <= smallest.size()) {
//            freeLL(smallest);
                    smallest = (LinkedList<Integer>) (goDown).clone();
                    fastest = smallest.size();
                }
            }
            l.remove(nex2);
        }
        ////Move Right the Maze return pathway begin from root
        Integer nex3 = index + 1;
        if (ps.column + 1 < maze_size && !l.contains(nex3)) {
            l.add(nex3);
            Position r = new Position(ps.row, ps.column + 1);
            LinkedList<Integer> goRight = MazeSolver(maze, index + 1, l, r, curr + 1, fastest);
            if (goRight != null) {
//        if (root == 0) {
//            printf("\nRight Path:");
//            iterateList(goRight);
//        }
                if (smallest == null || goRight.size() <= smallest.size()) {
                    ////FIGURE OUT WHAT IS WRONG WITH BELOW FREE
//            freeLL(smallest);
                    smallest = (LinkedList<Integer>) goRight.clone();
                    fastest = smallest.size();
                }
            }
            l.remove(nex3);
        }
        ////Move Left the Maze return pathway begin from root
        Integer nex4 = index - 1;
        if (ps.column - 1 >= 0 && !l.contains(nex4)) {
            l.add(nex4);
            Position left = new Position(ps.row, ps.column - 1);
            LinkedList<Integer> goLeft = MazeSolver(maze, index - 1, l, left, curr + 1, fastest);
            if (goLeft != null) {
//        if (root == 0) {
//            printf("\nLeft Path:");
//            iterateList(goLeft);
//
//        }
                if (smallest == null || goLeft.size() <= smallest.size()) {
//            freeLL(smallest);
                    smallest = (LinkedList<Integer>) goLeft.clone();
                    fastest = smallest.size();
                }
            }
            l.remove(nex4);
        }
        if (smallest != null) {
            return smallest;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        int index = 0;
        int array[] = new int[maze_size * maze_size];
        Position p = new Position(0, 0);
        for (int i = 0; i < maze_size * maze_size; i++) {
            if (maze[i] == s) {
                index = i;
                break;
            } else {
                p.column++;
                if (p.column > maze_size - 1) {
                    p.column = 0;
                    p.row++;
                }
            }
        }
        if (p.row > maze_size - 1) {
            System.out.println("Can't find start!\n");
        } else {
            Integer initial = index;
            LinkedList<Integer> start = new LinkedList<>();
            start.add(initial);
            LinkedList<Integer> path = MazeSolver(maze, index, start, p, 0, 0);
            if (path != null) {
                for (int i = 0; i < maze_size * maze_size; i++) {
                    for (Integer in : path) {
                        if (in.intValue() == i) {
                            array[i] = maze[i];
                            break;
                        }
                    }
                }
                System.out.print(" Original Maze:                             " +
                        "Soln Maze: " + path.size() + " Points\n");
                int maz = 0;
                int mult = 1;
                while (maz < maze_size * maze_size) {
                    for (int stretch = maz; stretch < maze_size * mult; stretch++) {
                        System.out.print(" " + maze[stretch]);
                    }
                    System.out.print("   ");
                    for (int stretch = maz; stretch < maze_size * mult; stretch++) {
                        System.out.print(" " + array[stretch]);
                    }
                    maz += maze_size;
                    mult++;
                    System.out.print("\n");
                }
            } else {
                System.out.println("No solution!\n");
                int mazf = 0;
                int mult2 = 1;
                while (mazf < maze_size * maze_size) {
                    for (int stretch = mazf; stretch < maze_size * mult2; stretch++) {
                        System.out.print(" " + maze[stretch]);
                    }
                    mazf += maze_size;
                    mult2++;
                    System.out.print("\n");
                }


            }
        }


        // write your code here
    }
}
