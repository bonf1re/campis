package campiss.dp1.model;

import java.util.*;

public class TabuList
{
    private ArrayList<TabuSolution> solutions;

    public TabuList()
    {
        this.solutions = new ArrayList<TabuSolution>();
    }

    private void addSolution(TabuSolution s)
    {
        this.solutions.add(s);
    }

    public ArrayList<TabuSolution> getSolutions()
    {
        return this.solutions;
    }

    public void update(TabuSolution current)
    {
        for (int i = 0; i < this.solutions.size(); i++) {
            if (this.solutions.get(i).getCount() == 1) {
                this.solutions.remove(i);
                i--;
            } else {
                this.solutions.get(i).setCount(this.solutions.get(i).getCount() - 1);
            }
        }

        TabuSolution bannedSolution = new TabuSolution(current);
        bannedSolution.setCount(10);
        this.addSolution(bannedSolution);
    }
}
