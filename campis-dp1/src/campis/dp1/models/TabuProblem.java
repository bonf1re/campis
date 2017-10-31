package campis.dp1.models;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TabuProblem
{
    private Integer[][] map = new Integer[60][30];

    public TabuProblem(Integer[][] map)
    {
        this.map = map;
    }

    public TabuSolution initial(ArrayList<Coordinates> order)
    {
        Collections.shuffle(order);
        return new TabuSolution(order);
    }

    private Double euclidean(Coordinates first, Coordinates second)
    {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
    }

    public Double distance(TabuSolution solution)
    {
        Double distance = 0.0;
        Coordinates origin = new Coordinates();
        origin.setX(0);
        origin.setY(0);

        distance += this.euclidean(origin, solution.getOrder().get(0));

        for(int i = 0; i < solution.getOrder().size() - 1; i++) {
            Double step = this.euclidean(solution.getOrder().get(i), solution.getOrder().get(i+1));
            distance += step;    
        }

        distance += this.euclidean(solution.getOrder().get(solution.getOrder().size() - 1), origin);

        return distance;
    }

    public boolean isBetter(TabuSolution s1, TabuSolution s2)
    {
        return (this.distance(s1) < this.distance(s2));
    }

    public TabuSolution bestNeighbor(ArrayList<TabuSolution> candidates, TabuList tabuList)
    {
        Collections.sort(candidates, (TabuSolution s1, TabuSolution s2) 
                -> this.distance(s1).compareTo(this.distance(s2)));

        for (int i = 0; i < candidates.size(); i++) {
            boolean found = false;

            for (int j = 0; j < tabuList.getSolutions().size(); j++) {
                if (candidates.get(i).stringFormatOrder().equals(tabuList.getSolutions().get(j).stringFormatOrder())) {
                    found = true;
                    break;
                }
            }

            if (found == false) {
                return candidates.get(i);
            }
        }

        return candidates.get(0);
    }

    public boolean stop(TabuSolution best, Integer iterations) 
    {
        return (iterations >= 100000 || best.getCount() == 2500);
    }
}
