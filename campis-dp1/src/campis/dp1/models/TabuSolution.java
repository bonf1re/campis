package campis.dp1.models;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TabuSolution
{
    private ArrayList<Coordinates> order = new ArrayList<Coordinates>();
    private int count;

    public TabuSolution(ArrayList<Coordinates> order)
    {
        this.order = order;
        this.count = 0;
    }

    public TabuSolution(TabuSolution solution)
    {
        ArrayList<Coordinates> order = new ArrayList<Coordinates>(solution.getOrder().size());

        for (int i = 0; i < solution.getOrder().size(); i++) {
            order.add(solution.getOrder().get(i));
        }
        
        this.order = order;
        this.count = 0;
    }

    public int getCount()
    {
        return this.count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public ArrayList<Coordinates> getOrder()
    {
        return this.order;
    }

    public void increaseCount()
    {
        this.count++;
    }

    public String stringFormatOrder()
    {
        String order = "";

        for (int i = 0; i < this.order.size(); i++) {
            order = order + "[" + this.order.get(i).stringFormat() + "], ";
        }

        return order;
    }

    public void printOrder()
    {
        out.println(this.stringFormatOrder());
    }

    public void swap(int first, int second)
    {
        Collections.swap(this.order, first, second);
    }

    public ArrayList<TabuSolution> neighbors()
    {
        ArrayList<TabuSolution> neighbors = new ArrayList<TabuSolution>();
        neighbors.add(new TabuSolution(this));

        for (int i = 0; i < this.order.size(); i++) {
            for (int j = i + 1; j < this.order.size(); j++) {
                TabuSolution neighbor = new TabuSolution(this);
                neighbor.swap(i, j);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }
}
