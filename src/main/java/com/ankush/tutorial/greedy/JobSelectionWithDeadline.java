package com.ankush.tutorial.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JobSelectionWithDeadline {

    public static class Job {

        int id;
        int deadline;
        int profit;

        public Job(int id, int deadline, int profit) {
            this.deadline = deadline;
            this.id = id;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "id=" + id +
                    ", deadline=" + deadline +
                    ", profit=" + profit +
                    '}';
        }

        public static class JobProfitComparator implements Comparator<Job> {

            @Override
            public int compare(Job j1, Job j2) {
                return j2.profit - j1.profit;
            }
        }
    }

    public static class Disjoint {
        int[] parent;
        int n;

        public Disjoint(int n) {
            this.n = n+1;
            this.parent = new int[n+1];
            init(parent);
        }

        private void init(int[] parent) {
            for (int i = 0; i< parent.length; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) return x;

            int result = find(parent[x]);
            parent[x] = result;
            return result;
        }

        public void union(int x1, int x2) {
            parent[x2] = x1;
        }
    }

    public static List<Job> getSelectedJobs(List<Job> jobs) {
        Collections.sort(jobs, new Job.JobProfitComparator());
        System.out.println(jobs);
        int findMaxDeadline = findMaxDeadline(jobs);
        Disjoint set = new Disjoint(findMaxDeadline);
        List<Job> selectedJobs = new ArrayList<>();
        display(set.parent);

        for (Job job : jobs) {
            int availableSlot = set.find(job.deadline);
            System.out.println("available slot = "+availableSlot);
            if (availableSlot > 0) {
                selectedJobs.add(job);
                set.union(set.find(availableSlot - 1), availableSlot);
                display(set.parent);
            }
        }
        return selectedJobs;
    }

    private static int findMaxDeadline(List<Job> jobs) {
        int max = jobs.get(0).deadline;
        for (int i =1 ; i<jobs.size(); i++) {
            if (max < jobs.get(i).deadline) {
                max = jobs.get(i).deadline;
            }
        }
        return max;
    }
    private static void display(int[] arr) {
        System.out.println();
        for(int i = 0; i<arr.length; i++) {
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(1,2,100));
        jobs.add(new Job(2,1,19));
        jobs.add(new Job(3,2,27));
        jobs.add(new Job(4,2,25));
        jobs.add(new Job(5,3,15));
        System.out.println(getSelectedJobs(jobs));



    }
}
