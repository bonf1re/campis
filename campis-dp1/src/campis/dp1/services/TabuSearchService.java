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
    private TabuUtils tUtils;

    public TabuSearchService()
    {
        this.tabuList = new TabuList();
        this.tUtils = new TabuUtils();
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

            ArrayList<TabuSolution> candidates = this.tUtils.vecinos(current);
            TabuSolution nuevaSol = problema.mejorVecino(candidatos, this.listaT); 

            out.println("Mejor solución vecina posible:");
            out.println("Distancia: " + problema.distancia(nuevaSol));
            nuevaSol.imprimirOrden();

            actual = nuevaSol; 
            this.listaT.actualizar(actual); 

            if (problema.esMejor(actual, mejor)) { 
                mejor = actual; 
                out.println("Nueva mejor solución encontrada");
            } else { 
                mejor.aumentarCount(); 
                out.println("Se mantiene mejor solución");
            } 

            out.println("Mejor solución actual:");
            out.println("Distancia: " + problema.distancia(mejor));
            mejor.imprimirOrden();
            out.println("Contador: " + mejor.getCount());
            
            iteracion++;
        } 

        return mejor;
    }
}
