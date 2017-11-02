package campis.dp1.services;

import campis.dp1.models.Coordinates;
import campis.dp1.models.TabuList;
import campis.dp1.models.TabuProblem;
import campis.dp1.models.TabuSolution;
import java.util.ArrayList;
import static java.lang.System.out;

public class TabuSearchService
{
    private TabuList tabuList;

    public TabuSearchService()
    {
        this.tabuList = new TabuList();
    }

    public TabuSolution search(TabuProblem problem, ArrayList<Coordinates> order) 
    { 
        TabuSolution current = problem.initial(order); 
        TabuSolution best = current; 
        
        Integer iteration = 0; 
        
        while (!problem.stop(best, iteration)) { 
            out.println("=================================");
            out.println("Iteración Nro " + iteration);
            out.println("Mejor solución actual:");
            out.println("Distancia: " + problem.distance(best));
            best.printOrder();
            out.println("Ultima solución evaluada:");
            out.println("Distancia: " + problem.distance(current));
            current.printOrder();

            ArrayList<TabuSolution> candidates = current.neighbors();
            TabuSolution newSolution = problem.bestNeighbor(candidates, this.tabuList); 

            out.println("Mejor solución vecina posible:");
            out.println("Distancia: " + problem.distance(newSolution));
            newSolution.printOrder();

            current = newSolution; 
            this.tabuList.update(current); 

            if (problem.isBetter(current, best)) { 
                best = current; 
                out.println("Nueva mejor solución encontrada");
            } else { 
                best.increaseCount(); 
                out.println("Se mantiene mejor solución");
            } 

            out.println("Mejor solución actual:");
            out.println("Distancia: " + problem.distance(best));
            best.printOrder();
            out.println("Contador: " + best.getCount());
            
            iteration++;
        } 
        
        return best;
    }
}
