package campis.dp1.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class InputManager
{
    public Integer[][] cargarMatriz(String mapFile) throws FileNotFoundException, IOException
    {
        Integer[][] matriz = new Integer[60][30];
        BufferedReader mapR = new BufferedReader(new FileReader(mapFile));
            
        for (int y = 0; y < 30; y++) {
            String linea = mapR.readLine();
            for (int x = 0; x < 60; x++) {
                matriz[x][y] = Character.getNumericValue(linea.charAt(x));
            }
        }

        mapR.close();

        return matriz;
    }

    public ArrayList<Coordinates> cargarOrden(String orderFile) throws FileNotFoundException, IOException
    {
        ArrayList<Coordinates> orden = new ArrayList<Coordinates>();
        BufferedReader orderR = new BufferedReader(new FileReader(orderFile));

        for (int i = 0; i < 50; i++) {
            String linea = orderR.readLine();
            linea = linea.replace("[", "");
            linea = linea.replace("]", "");
            String[] tokens = linea.split(",");
            Coordinates nuevasCoord = new Coordinates();
            nuevasCoord.setY(Integer.parseInt(tokens[0]));
            nuevasCoord.setX(Integer.parseInt(tokens[1]));
            orden.add(nuevasCoord);
        }

        return orden;
    }
}
