import java.util.*;
import java.io.*;

public class mentorship {
    public static void main(String[] args) throws IOException {
        String[] inputs = new String[] {"a_an_example.in.txt", "b_better_start_small.in.txt", "c_collaboration.in.txt", "d_dense_schedule.in.txt", "e_exceptional_skills.in.txt", "f_find_great_mentors.in.txt"};
        for(int tc = 0; tc <= 5; tc++) {
            BufferedReader in = new BufferedReader(new FileReader(inputs[tc]));
            PrintWriter out = new PrintWriter("output" + tc + ".txt");
            StringTokenizer line = new StringTokenizer(in.readLine());
            int c = Integer.parseInt(line.nextToken());
            int p = Integer.parseInt(line.nextToken());
            Person[] ppl = new Person[c];
            HashMap<String, TreeSet<Person>> by_skill = new HashMap<String, TreeSet<Person>>();
            for(int i = 0; i < c; i++) {
                line = new StringTokenizer(in.readLine());
                String name = line.nextToken();
                int n = Integer.parseInt(line.nextToken());
                HashMap<String, Integer> skills = new HashMap<String, Integer>();
                ppl[i] = new Person(name);
                ppl[i].skills = skills;
                for(int j = 0; j < n; j++) {
                    line = new StringTokenizer(in.readLine());
                    String sname = line.nextToken();
                    int level = Integer.parseInt(line.nextToken());
                    skills.put(sname, level);
                    if(!by_skill.containsKey(sname)) {
                        TreeSet<Person> sorted = new TreeSet<Person>(new Comparator<Person>() {
                            @Override
                            public int compare(Person a, Person b) {
                                int askill = a.skills.get(sname);
                                int bskill = b.skills.get(sname);
                                if(askill == bskill) {
                                    return a.name.compareTo(b.name);
                                }
                                return askill - bskill;
                            }
                        });
                        by_skill.put(sname, sorted);
                    }
                    by_skill.get(sname).add(ppl[i]);
                }
            }
            TreeSet<Project> projects = new TreeSet<Project>();
            for(int i = 0; i < p; i++) {
                line = new StringTokenizer(in.readLine());
                Project cur = new Project(line.nextToken(), Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
                int r = Integer.parseInt(line.nextToken());
                String[] skills = new String[r];
                int[] levels = new int[r];
                for(int j = 0; j < r; j++) {
                    line = new StringTokenizer(in.readLine());
                    skills[j] = line.nextToken();
                    levels[j] = Integer.parseInt(line.nextToken());
                }
                cur.skills = skills;
                cur.levels = levels;
                cur.calc_weight();
                projects.add(cur);
            }
            ArrayList<String> completed = new ArrayList<String>();
            ArrayList<String> assignments = new ArrayList<String>();
            while(true) {
                ArrayList<Project> complete = new ArrayList<Project>();
                HashSet<Person> used_total = new HashSet<Person>();
                for(Project proj : projects) {
                    String[] skills = proj.skills;
                    int[] levels = proj.levels;
                    HashSet<Person> used = new HashSet<Person>(); 
                    boolean possible = true;
                    StringBuilder assigned = new StringBuilder();
                    ArrayList<Person> improve = new ArrayList<Person>();
                    ArrayList<String> skill = new ArrayList<String>();
                    for(int i = 0; i < skills.length; i++) {
                        TreeSet<Person> candidates = by_skill.get(skills[i]);
                        boolean found = false;
                        for(Person candidate : candidates) {
                            int cskill = candidate.skills.get(skills[i]);
                            if(cskill >= levels[i] && !used_total.contains(candidate) && !used.contains(candidate)) {
                                used.add(candidate);
                                assigned.append(candidate.name);
                                assigned.append(' ');
                                if(cskill == levels[i]) {
                                    improve.add(candidate);
                                    skill.add(skills[i]);
                                }
                                found = true;
                                break;
                            }
                        }
                        if(!found) {
                            possible = false;
                            break;
                        }
                    }
                    if(possible) {
                        complete.add(proj);
                        completed.add(proj.name);
                        assignments.add(assigned.toString());
                        for(int i = 0; i < improve.size(); i++) {
                            Person improved = improve.get(i);
                            String improv_skill = skill.get(i);
                            by_skill.get(improv_skill).remove(improved);
                            improved.skills.put(improv_skill, improved.skills.get(improv_skill) + 1);
                            by_skill.get(improv_skill).add(improved);
                        }
                        for(Person p_use : used) {
                            used_total.add(p_use);
                        }
                    }
                }
                if(complete.size() == 0) {
                    break;
                }else {
                    for(Project proj : complete) {
                        projects.remove(proj);
                    }
                }
            }
            out.println(completed.size());
            for(int i = 0; i < completed.size(); i++) {
                out.println(completed.get(i));
                out.println(assignments.get(i));
            }
            in.close();
            out.close();
        }
    }
    static class Person {
        String name;
        HashMap<String, Integer> skills;
        Person(String n) {
            name = n;
        }
    }
    static class Project implements Comparable<Project> {
        String name;
        int duration, score, due;
        double weight;
        String[] skills;
        int[] levels;
        Project(String n, int d, int s, int du) {
            name = n;
            duration = d;
            score = s;
            due = du;
        }
        void calc_weight() {
            weight = (double)score / due - duration - skills.length;
        }
        @Override
        public int compareTo(Project o) {
            if(weight > o.weight) {
                return -1;
            }else if(weight < o.weight) {
                return 1;
            }else {
                return name.compareTo(o.name);
            }
        }
    }
}
