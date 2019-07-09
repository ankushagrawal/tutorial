package com.ankush.tutorial.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinimizeLoss {

    public static class Job {
        int id;
        int loss;
        int daysRequired;

        public Job(int id, int loss, int daysRequired) {
            this.id = id;
            this.loss = loss;
            this.daysRequired = daysRequired;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "id=" + id +
                    ", loss=" + loss +
                    ", daysRequired=" + daysRequired +
                    '}';
        }

        public static class JobLossComparator implements Comparator<Job> {

            @Override
            public int compare(Job j1, Job j2) {
                return j2.loss * j1.daysRequired - j2.daysRequired * j1.loss;
            }
        }
    }

    public static void orderedJobForMimimumLoss(List<Job> jobs) {
        System.out.println("actual jobs = "+jobs);
        Collections.sort(jobs, new Job.JobLossComparator());
        System.out.println("ordered jobs = "+jobs);

    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(1,1,2));
        jobs.add(new Job(2,2,4));
        jobs.add(new Job(3,3,1));
        jobs.add(new Job(4,5,3));
        jobs.add(new Job(5,6,2));
        orderedJobForMimimumLoss(jobs);

    }

}
