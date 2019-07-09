package com.ankush.tutorial.greedy;

import java.util.*;

public class ActivitySelection {

    private static class ActivitySelectionOutput{
        int maxNoOfActivities;
        List<Activity> activities;

        @Override
        public String toString() {
            return "ActivitySelectionOutput{" +
                    "maxNoOfActivities=" + maxNoOfActivities +
                    ", activities=" + activities +
                    '}';
        }
    }

    private static class Activity{
        int start;
        int end;
        public Activity(int start, int end){
            this.start = start;
            this.end = end;
        }


        @Override
        public String toString() {
            return "Activity{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    private static class ActivityComparatorByEndTime implements Comparator<Activity>{

        @Override
        public int compare(Activity a1, Activity a2) {
            return a1.end - a2.end;
        }
    }

    private static ActivitySelectionOutput getMaxActivities(int[] start, int[] end) {
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            activities.add(new Activity(start[i], end[i]));
        }

        //print Activity
        printActivites(activities);

        //sort activities based on finish time
        Collections.sort(activities, new ActivityComparatorByEndTime());

        printActivites(activities);

        //pick first activity
        ActivitySelectionOutput activitySelectionOutput = new ActivitySelectionOutput();
        int max = 1;
        int lastSelected = 0;
        activitySelectionOutput.activities = new ArrayList<>();
        activitySelectionOutput.activities.add(activities.get(0));

        for (int i = 1; i < activities.size(); i++) {
            if (activities.get(i).start >= activities.get(lastSelected).end) {
                max++;
                lastSelected = i;
                activitySelectionOutput.activities.add(activities.get(i));
            }
        }

        activitySelectionOutput.maxNoOfActivities = max;

        return activitySelectionOutput;

    }

    private static void printActivites(List<Activity> activities) {
        System.out.println("activities = "+ activities);
    }

    public static void main(String[] args) {
        int[] start = new int[]{5,1,3,0,5,8};
        int[] finish = new int[]{9,2,4,6,7,9};
        ActivitySelectionOutput output = getMaxActivities(start,finish);
        System.out.println("output = "+output);
    }
}
