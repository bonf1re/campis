/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.racks;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.CRack;
import campis.dp1.models.Rack;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseZone;
import campis.dp1.models.utils.GraphicsUtils;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gina Bustamante
 */
public class CreateRackController implements Initializable {

    private Main main;
    private GraphicsContext gc;
    private int[][] real_map;
    private ArrayList<CRack> crackList;
    private int x;
    private int y;
    private int warehouse_id;
    private Warehouse wareh = new Warehouse();

    @FXML
    private JFXTextField columnsField;
    @FXML
    private JFXTextField floorsField;
    @FXML
    private JFXTextField x_Field;
    @FXML
    private JFXTextField y_Field;
    @FXML
    private JFXComboBox<Integer> orientationField;
    @FXML
    private Canvas mapCanvas;
    @FXML
    private Label messageField;

    @FXML
    private void goListRacks() throws IOException {
        ContextFX.getInstance().setId(this.warehouse_id);
        main.showListRacks();
    }

    private List<Rack> getRacks(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rack.class);
        criteria.add(Restrictions.eq("id_warehouse", cod));
        List<Rack> list = criteria.list();
        List<Rack> returnable = list;
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private Warehouse getWarehouseDimensions() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class);
        criteria.add(Restrictions.eq("id_warehouse", warehouse_id));
        List<Warehouse> list = criteria.list();
        Warehouse returnable = list.get(0);
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private boolean verifyRacks() {
        boolean returnable = TRUE;
        List<Rack> racks;
        racks = getRacks(warehouse_id);
        Warehouse dimensions = getWarehouseDimensions();
        if (this.orientationField.getValue() == 0) {
            Integer x_pos = Integer.parseInt(x_Field.getText());
            Integer y_pos = Integer.parseInt(y_Field.getText());
            if (x_pos > dimensions.getLength() || y_pos > dimensions.getWidth()) {
                returnable = FALSE;
            } else {
                for (int i = 0; i < racks.size(); i++) {
                    Integer posXini = racks.get(i).getPos_x();
                    Integer posXfin = racks.get(i).getPos_x() + racks.get(i).getN_columns();
                    Integer posYini = racks.get(i).getPos_y();
                    Integer posYfin = racks.get(i).getPos_y() + 2;
                    if (((x_pos > posXfin + 1) || (x_pos < posXini - 1)) && ((y_pos > posYfin + 1) || (y_pos < posYini - 1))) {
                        returnable = TRUE;
                    } else {
                        returnable = FALSE;
                        break;
                    }
                }
            }
        } else if (this.orientationField.getValue() == 1) {
            Integer x_pos = Integer.parseInt(x_Field.getText());
            Integer y_pos = Integer.parseInt(y_Field.getText());
            if (x_pos > dimensions.getLength() || y_pos > dimensions.getWidth()) {
                returnable = FALSE;
            } else {
                for (int i = 0; i < racks.size(); i++) {
                    Integer posXini = racks.get(i).getPos_x();
                    Integer posXfin = racks.get(i).getPos_x() + 2;
                    Integer posYini = racks.get(i).getPos_y();
                    Integer posYfin = racks.get(i).getPos_y() + racks.get(i).getN_columns();
                    if (((x_pos > posXfin + 1) || (x_pos < posXini - 1)) && ((y_pos > posYfin + 1) || (y_pos < posYini - 1))) {
                        returnable = TRUE;
                    } else {
                        returnable = FALSE;
                        break;
                    }
                }
            }
        }
        return returnable;
    }

    @FXML
    private void insertRack() throws IOException {

        //int orientation = orientationCb.getSelectionModel().getSelectedIndex();
        boolean flag = verifyRacks();
        if (flag == TRUE) {
            Rack r = new Rack(this.warehouse_id, Integer.parseInt(x_Field.getText()), Integer.parseInt(y_Field.getText()),
                    Integer.parseInt(columnsField.getText()), Integer.parseInt(floorsField.getText()),
                    0);

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.save(r);
            // create a zone for each column and floor
            int orientation = r.getOrientation();
            int init_y = r.getPos_y();
            int init_x = r.getPos_x();
            for (int i = 0; i < r.getN_columns(); i++) {
                for (int j = 0; j < r.getN_floors(); j++) {
                    for (int k = 0; k < 2; k++) {
                        int y = init_y;
                        int x = init_x;
                        int z = j;
                        if (orientation == 0) {
                            x += i;
                            y += k;
                        } else {
                            y += 1;
                            x += k;
                        }
                        WarehouseZone zone = new WarehouseZone(r.getId_warehouse(), r.getId_rack(), x, y, z, true);
                        //session.beginTransaction();
                        System.out.println("ok");
                        session.save(zone);
                    }
                    
                    
                }
                
               
            } 
            
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            
            this.goListRacks();
        }
    }

    private ArrayList<CRack> getCRacks() {
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
        session.close();
        sessionFactory.close();
        
        return crackList;
    }

    @FXML
    private void clearAction() {
        GraphicsUtils gu = new GraphicsUtils();
        this.real_map = gu.initMap(this.y, this.x);
        this.crackList = gu.putCRacks(wareh.getId(), real_map);
        gu.drawVisualizationMap(gc, this.y, this.x, this.real_map);
    }

    @FXML
    private void testAction() {
        if (Integer.parseInt(x_Field.getText()) > 0 && Integer.parseInt(y_Field.getText()) > 0
                && Integer.parseInt(columnsField.getText()) > 0 && Integer.parseInt(floorsField.getText()) > 0) {
            GraphicsUtils gu = new GraphicsUtils();
            ArrayList<CRack> crackListAux = new ArrayList<>();
            crackListAux = getCRacks();
            Rack rackAux = new Rack(this.warehouse_id, Integer.parseInt(x_Field.getText()), Integer.parseInt(y_Field.getText()),
                    Integer.parseInt(columnsField.getText()), Integer.parseInt(floorsField.getText()),
                    orientationField.getValue());
            CRack cRackAux = new CRack(rackAux);
            crackListAux.add(cRackAux);
            for (CRack rack : crackListAux) {
                int rack_y = rack.getPos_y();
                int rack_x = rack.getPos_x();
                int rack_length = rack.getN_columns();
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < rack_length; j++) {
                        real_map[i + rack_y][j + rack_x] = 2;
                    }
                }
            }
            gu.drawVisualizationMap(gc, this.y, this.x, this.real_map);
            boolean flag = verifyRacks();
            if (flag == TRUE) {
                messageField.setText("Se puede crear el Rack");
            } else {
                messageField.setText("No se puede crear el Rack, seleccione otras posiciones");
            }
        }else{
            messageField.setText("Los valores introducidos deben ser mayores a 0");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.warehouse_id = ContextFX.getInstance().getId();
        x_Field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    x_Field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        y_Field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    y_Field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        columnsField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    columnsField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        floorsField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    floorsField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        orientationField.getItems().addAll(0, 1);
        GraphicsUtils gu = new GraphicsUtils();
        gc = mapCanvas.getGraphicsContext2D();
        wareh = getWarehouseDimensions();
        this.y = wareh.getWidth();
        this.x = wareh.getLength();
        this.real_map = gu.initMap(this.y, this.x);
        this.crackList = gu.putCRacks(wareh.getId(), real_map);
        gu.drawVisualizationMap(gc, this.y, this.x, this.real_map);
    }
}
