/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models.utils;

import campis.dp1.models.BatchWH_Move;
import campis.dp1.models.CRack;
import campis.dp1.models.Coord;
import campis.dp1.models.Rack;
import campis.dp1.models.WarehouseZone;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sergio
 */
public class GraphicsUtils {

    public GraphicsUtils() {
        super();
    }

    public int[][] initMap(int y, int x) {
        int[][] map = new int[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                map[i][j] = 0;
            }
        }
        return map;
    }

    public ArrayList<CRack> putCRacks(int warehouse_id, int[][] real_map) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rack.class);
        criteria.add(Restrictions.eq("id_warehouse", warehouse_id));
        List racks = criteria.list();
        ArrayList<CRack> crackList = new ArrayList<>();
        for (int i = 0; i < racks.size(); i++) {
            crackList.add(new CRack((Rack) racks.get(i)));
        }

        for (CRack rack : crackList) {
            int rack_y = rack.getPos_y();
            int rack_x = rack.getPos_x();
            int rack_length = rack.getN_columns();
            if (rack.getOrientation() == 0) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < rack_length; j++) {
                        real_map[i + rack_y][j + rack_x] = 1;
                    }
                }
            } else if (rack.getOrientation() == 1) {
                for (int i = 0; i < rack_length; i++) {
                    for (int j = 0; j < 2; j++) {
                        real_map[i + rack_y][j + rack_x] = 1;
                    }
                }
            }
        }
        session.close();
        sessionFactory.close();
        return crackList;
    }

    @FXML
    public void drawVisualizationMap(GraphicsContext gc, int y, int x, int[][] map) {
        float canvas_height = (float) gc.getCanvas().getHeight();
        float canvas_width = (float) gc.getCanvas().getWidth();
        int mult = 1;
        int padding_y = 0;
        int padding_x = 0;
        float scaling_factor_y = 1;
        float scaling_factor_x = 1;
        
        // Padding / Scaling 
        if (canvas_width / x > 1 && canvas_height / y > 1) {
            // Padding 
            if (canvas_width / x > canvas_height / y) {
                mult = (int) canvas_height / y;
                //mult = (int) canvas_height/x;
            } else {
                mult = (int) canvas_width / x;
                //mult = (int) canvas_width/y;
            }

            padding_y = (int) (((int) canvas_height) - mult * y) / 2;
            padding_x = (int) (((int) canvas_width) - mult * x) / 2;
            
            
            
            if (padding_y > 0) {
                padding_y += mult / 2;
            }
            if (padding_x > 0) {
                padding_x += mult / 2;
            }

            System.out.println(padding_x);
            System.out.println(padding_y);
            System.out.println(mult);
        } else {
            // Scaling
            scaling_factor_y = y / canvas_height;
            scaling_factor_x = x / canvas_width;
            if (scaling_factor_y > scaling_factor_x) {
                // padding at x
                padding_x = (int) Math.abs(canvas_width - x / scaling_factor_x) / 2;
            } else {
                // padding at y
                padding_y = (int) Math.abs(canvas_height - y / scaling_factor_y) / 2;
            }

        }
        
        
        

        for (int j = 0; j < y; j += scaling_factor_y) {
            for (int i = 0; i < x; i += scaling_factor_x) {
                if (map[j][i] == 1) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(i * mult / scaling_factor_x + padding_x, j * mult / scaling_factor_y + padding_y, mult, mult);
                }
                if (map[j][i] == 0) {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(i * mult / scaling_factor_x + padding_x, j * mult / scaling_factor_y + padding_y, mult, mult);
                }
                if (map[j][i] == 2) {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i * mult / scaling_factor_x + padding_x, j * mult / scaling_factor_y + padding_y, mult, mult);
                }
            }
        }
        
        for (int j = 0; j < y; j+= scaling_factor_y) {
            for (int i = 0; i < x; i+= scaling_factor_y) {
                gc.setStroke(Color.LIGHTBLUE);
                gc.setFill(Color.TRANSPARENT);
                gc.strokeRect(i * mult / scaling_factor_x + padding_x, j * mult / scaling_factor_y + padding_y, mult, mult);
            }
        }
    }

    @FXML
    public void drawVisualizationMap(GraphicsContext gc, int y, int x, int[][] map, ArrayList<Coord> route, ObservableList<BatchWH_Move> b_zones) {
        //System.out.println(ContextFX.getInstance().getId());
        // String filename = "/media/Multimedia/Projects/GitProjects/GRASP-OPT2/Inputs/map_0.txt";
        // read_map(filename);

        /// Variable intial setup
        float canvas_height = (float) gc.getCanvas().getHeight();
        float canvas_width = (float) gc.getCanvas().getWidth();
        int mult = 1;
        int padding_y = 0;
        int padding_x = 0;
        float scaling_factor_y = 1;
        float scaling_factor_x = 1;

        // Padding / Scaling 
        if (canvas_width / x > 1 && canvas_height / y > 1) {
            // Padding 
            if (canvas_width / x > canvas_height / y) {
                mult = (int) canvas_height / y;
                //mult = (int) canvas_height/x;
            } else {
                mult = (int) canvas_width / x;
                //mult = (int) canvas_width/y;
            }

            padding_y = (int) (((int) canvas_height) - mult * y) / 2;
            padding_x = (int) (((int) canvas_width) - mult * x) / 2;

            if (padding_y > 0) {
                padding_y += mult / 2;
            }
            if (padding_x > 0) {
                padding_x += mult / 2;
            }

            System.out.println(padding_x);
            System.out.println(padding_y);
            System.out.println(mult);
        } else {
            // Scaling
            scaling_factor_y = y / canvas_height;
            scaling_factor_x = x / canvas_width;
            if (scaling_factor_y > scaling_factor_x) {
                // padding at x
                padding_x = (int) Math.abs(canvas_width - x / scaling_factor_x) / 2;
            } else {
                // padding at y
                padding_y = (int) Math.abs(canvas_height - y / scaling_factor_y) / 2;
            }

        }

        for (int j = 0; j < y; j += scaling_factor_y) {
            for (int i = 0; i < x; i += scaling_factor_x) {
                if (map[j][i] == 1) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(i * mult / scaling_factor_x + padding_x, j * mult / scaling_factor_y + padding_y, mult, mult);
                }
                if (map[j][i] == 0) {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(i * mult / scaling_factor_x + padding_x, j * mult / scaling_factor_y + padding_y, mult, mult);
                }
                if (map[j][i] == 2) {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i * mult / scaling_factor_x + padding_x, j * mult / scaling_factor_y + padding_y, mult, mult);
                }
            }
        }
        
        
        for (int j = 0; j < y; j+= scaling_factor_y) {
            for (int i = 0; i < x; i+= scaling_factor_y) {
                gc.setStroke(Color.LIGHTBLUE);
                gc.setFill(Color.TRANSPARENT);
                gc.strokeRect(i * mult / scaling_factor_x + padding_x, j * mult / scaling_factor_y + padding_y, mult, mult);
            }
        }
        
        for (BatchWH_Move b_zone : b_zones) {
            gc.setStroke(Color.LAVENDER);
            gc.setFill(Color.LAVENDER);
            gc.fillRect(b_zone.getZone().getPos_x() * mult / scaling_factor_x + padding_x, b_zone.getZone().getPos_y() * mult / scaling_factor_y + padding_y, mult, mult);
        }
        

        // Draw route
        for (int k = 0; k < route.size() - 1; k++) {
            // paint line between coord i n coord i+1
            gc.setStroke(Color.RED);
            int y_i = route.get(k).y;
            int x_i = route.get(k).x;
            int y_ii = route.get(k + 1).y;
            int x_ii = route.get(k + 1).x;
            if (y_i == y_ii || x_i == x_ii) {

                // Straight painting
                gc.strokeLine(x_i * mult / scaling_factor_x + padding_x,
                        y_i * mult / scaling_factor_y + padding_y,
                        x_ii * mult / scaling_factor_x + padding_x,
                        y_ii * mult / scaling_factor_y + padding_y);
            } else {

                // for diagonal painting
                Coord c1 = new Coord(y_i, x_i);
                Coord c2 = new Coord(y_ii, x_ii);
                int diff_y = c1.y - c2.y;
                int diff_x = c1.x - c2.x;
                int step_y = Math.abs((diff_y) / (diff_x)) == 0 ? 1 : Math.abs((diff_y) / (diff_x));
                int step_x = Math.abs((diff_x) / (diff_y)) == 0 ? 1 : Math.abs((diff_x) / (diff_y));
                int ini_y = c1.y;
                int ini_x = c1.x;

                do {
                    for (int i = 0; i < Math.abs(step_y); i++) {
                        if (overbound(ini_y, ini_x, c1, c2)) {
                        } else {
                            break;
                        }

                        gc.strokeLine(ini_x * mult / scaling_factor_x + padding_x,
                                ini_y * mult / scaling_factor_y + padding_y,
                                ini_x * mult / scaling_factor_x + padding_x,
                                (ini_y + (-1) * diff_y / Math.abs(diff_y)) * mult / scaling_factor_y + padding_y);

                        ini_y += (-1) * diff_y / Math.abs(diff_y);
                    }
                    for (int i = 0; i < Math.abs(step_x); i++) {
                        if (overbound(ini_y, ini_x, c1, c2)) {
                        } else {
                            break;
                        }

                        gc.strokeLine(ini_x * mult / scaling_factor_x + padding_x,
                                ini_y * mult / scaling_factor_y + padding_y,
                                (ini_x + (-1) * diff_x / Math.abs(diff_x)) * mult / scaling_factor_x + padding_x,
                                ini_y * mult / scaling_factor_y + padding_y);

                        ini_x += (-1) * diff_x / Math.abs(diff_x);
                    }
                } while (overbound(ini_y, ini_x, c1, c2));

                gc.strokeLine(ini_x * mult / scaling_factor_x + padding_x,
                        ini_y * mult / scaling_factor_y + padding_y,
                        x_ii * mult / scaling_factor_x + padding_x,
                        y_ii * mult / scaling_factor_y + padding_y);

            }
        }
        
         
    }

    private boolean overbound(int ini_y, int ini_x, Coord c1, Coord c2) {
        return ((ini_y >= c1.y && ini_y <= c2.y) || (ini_y >= c2.y && ini_y <= c1.y))
                && ((ini_x >= c1.x && ini_x <= c2.x) || (ini_x >= c2.x && ini_x <= c1.x));
    }
    
    // Buttons section
    
    public boolean popup2Options(String btext_header, String btext_content, String btext_confir, String btext_cancel){
        ButtonType b_ok = new ButtonType(btext_confir, ButtonBar.ButtonData.OK_DONE);
        ButtonType b_cancel = new ButtonType(btext_cancel, ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                                btext_content,
                                b_ok,
                                b_cancel);
        Text text = new Text(btext_content);
        text.setWrappingWidth(400);
        text.setTextAlignment(TextAlignment.CENTER);
        alert.getDialogPane().setContent(text);
        alert.setTitle("C A M P I S");
        alert.setHeaderText(btext_header);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == b_ok) {
            return true;
        }
        return false;
    }
    
    public void popupNotif(String btext_header, String btext_content, String btext_confir){
        ButtonType b_ok = new ButtonType(btext_confir, ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                                   btext_content,
                                   b_ok);
        Text text = new Text(btext_content);
        text.setWrappingWidth(400);
        text.setTextAlignment(TextAlignment.CENTER);
        alert.getDialogPane().setContent(text);
        alert.setTitle("C A M P I S");
        alert.setHeaderText(btext_header);
        alert.showAndWait();
    }
    
    public void popupError(String btext_header, String btext_content, String btext_confir){
        ButtonType b_ok = new ButtonType(btext_confir, ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.ERROR,
                                   btext_content,
                                   b_ok);
        Text text = new Text(btext_content);
        text.setWrappingWidth(400);
        text.setTextAlignment(TextAlignment.CENTER);
        alert.getDialogPane().setContent(text);
        alert.setTitle("C A M P I S");
        alert.setHeaderText(btext_header);
        alert.showAndWait();
    }
}
