package campis.dp1.models;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;

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

        for (int i = 0; i < 50; i++) {
            order = order + "[" + this.order.get(i).stringFormat() + "], ";
        }

        return order;
    }

    public void printOrder()
    {
        out.println(this.stringFormatOrder());
    }
}
