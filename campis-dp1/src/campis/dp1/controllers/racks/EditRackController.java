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
import campis.dp1.models.RackDisplay;
import campis.dp1.models.UnitOfMeasure;
import campis.dp1.models.Warehouse;
import campis.dp1.models.WarehouseZone;
import campis.dp1.models.utils.GraphicsUtils;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gina Bustamante
 */
public class EditRackController implements Initializable {

    Integer id;
    private Main main;
    private GraphicsContext gc;
    private int[][] real_map;
    private ArrayList<CRack> crackList;
    private int x;
    private int y;
    private int warehouse_id;
    private ObservableList<WarehouseZone> zones;
    private Warehouse wareh = new Warehouse();

    @FXML
    private JFXTextField numColumnsField;
    @FXML
    private JFXTextField numFloorsField;
    @FXML
    private JFXTextField pos_xField;
    @FXML
    private JFXTextField pos_yField;
    @FXML
    private JFXComboBox<String> orientationField;
    @FXML
    private Canvas mapCanvas;
    @FXML
    private Label messageField;
    @FXML
    private Button saveButton;

    @FXML
    private void goListRacks() throws IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rack.class);
        criteria.add(Restrictions.eq("id_rack", this.id));
        List rsWarehouse = criteria.list();
        Rack result = (Rack) rsWarehouse.get(0);
        session.close();
        sessionFactory.close();
        ContextFX.getInstance().setId(result.getId_warehouse());
        main.showListRacks();
    }

    @FXML
    private void editRack(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        int orient;
        if (orientationField.getValue().compareTo("Horizontal") == 0) {
            orient = 0;
        } else {
            orient = 1;
        }
        boolean flagZones = verifyZones();
        boolean flag = verifyRacks(orient);

        if (flagZones == TRUE && flag == TRUE) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            int orientation = searchOrientation(orientationField.getEditor().getText());

            Query query = session.createQuery("update Rack set"
                    + " pos_x = :newPos_x, pos_y = :newPos_y, n_columns = :newN_columns,"
                    + " n_floors = :newN_floors, orientation=:neworientation"
                    + " where id_rack = :id_rack");

            query.setParameter("newPos_x", Integer.parseInt(pos_xField.getText()));
            query.setParameter("newPos_y", Integer.parseInt(pos_yField.getText()));
            query.setParameter("newN_floors", Integer.parseInt(numFloorsField.getText()));
            query.setParameter("newN_columns", Integer.parseInt(numColumnsField.getText()));
            query.setParameter("neworientation", orientation);
            query.setParameter("id_rack", id);
            int result = query.executeUpdate();

            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
        } else if (!flagZones) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo eliminar el rack porque no todas las zonas estan vacías");
            alert.showAndWait();

            this.goListRacks();
        } else if (!flag) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo editar le rack, seleccione otras posiciones");
            alert.showAndWait();

            this.goListRacks();
        }

        this.goListRacks();
    }

    public static Integer searchOrientation(String orientation) {
        System.out.println("campis.dp1.controllers.racks.EditRackController.searchOrientation()");
        System.out.println(orientation);
        return 0;
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
        return crackList;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        id = ContextFX.getInstance().getId();

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rack.class);
        criteria.add(Restrictions.eq("id_rack", id));
        List rsType = criteria.list();
        Rack result = (Rack) rsType.get(0);

        session.close();
        sessionFactory.close();

        System.out.println(result.getId_rack());

        this.numColumnsField.setText(result.getN_columns().toString());
        this.numFloorsField.setText(result.getN_floors().toString());
        this.pos_xField.setText(result.getPos_x().toString());
        this.pos_yField.setText(result.getPos_y().toString());
        this.orientationField.getSelectionModel().select(result.getOrientation());
        //this.orientacionField.setText(result.getOrientation().toString());

        this.warehouse_id = result.getId_warehouse();
        //============================Nuevo=================================
        //orientationField.getItems().addAll(0, 1);
        orientationField.getItems().addAll("Horizontal", "Vertical");

        GraphicsUtils gu = new GraphicsUtils();
        gc = mapCanvas.getGraphicsContext2D();
        wareh = getWarehouseDimensions();
        this.y = wareh.getWidth();
        this.x = wareh.getLength();
        this.real_map = gu.initMap(this.y, this.x);
        this.crackList = gu.putCRacks(wareh.getId(), real_map);
        gu.drawVisualizationMap(gc, this.y, this.x, this.real_map);

        //Evaluamos si todas las zonas estan libres
        boolean flag = verifyZones();
        if (flag == TRUE) {
            //messageField.setText("Se puede editar el Rack");
        } else {
            messageField.setText("No se puede editar el Rack, no todas las zonas estan libres");
            this.saveButton.setDisable(true);
        }
    }

    private List<Rack> getRacks() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rack.class);
        List<Rack> list = criteria.list();
        List<Rack> returnable = list;
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private boolean verifyZones() {
        //Debes buscar en la tabla zones, si es que las zonas para ese racks... todas etsan vacias 
        zones = FXCollections.observableArrayList();
        zones = getZones();

        for (int i = 0; i < zones.size(); i++) {
            System.out.println(zones.get(i).isFree());
            if (!zones.get(i).isFree()) {
                return false;
            }
        }

        return true;
    }

    private ObservableList<WarehouseZone> getZones() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(WarehouseZone.class);
        criteria.add(Restrictions.eq("id_rack", this.id));
        List lista = criteria.list();
        //ArrayList<WarehouseZone> zonesList = new ArrayList<>();
        ObservableList<WarehouseZone> zonesList;
        zonesList = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            zonesList.add((WarehouseZone) lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return zonesList;
    }

    private boolean verifyRacks(int orient) {
        boolean returnable = TRUE;
        List<Rack> racks;
        racks = getRacks();
        Warehouse dimensions = getWarehouseDimensions();
        if (orient == 0) {
            Integer x_pos = Integer.parseInt(pos_xField.getText());
            Integer y_pos = Integer.parseInt(pos_yField.getText());
            if (x_pos > dimensions.getLength() || y_pos > dimensions.getWidth()
                    || (x_pos + Integer.parseInt(this.numColumnsField.getText()) == dimensions.getLength())
                    || (y_pos + 2 == dimensions.getWidth())) {
                returnable = FALSE;
            } else {
                for (int i = 0; i < racks.size(); i++) {
                    //si es el mismo racks no lo valida
                    if (Objects.equals(racks.get(i).getId_rack(), this.id)) {
                        System.out.println("Es el mismo rack");
                        continue;
                    }

                    System.out.println("campis.dp1.controllers.racks.EditRackController.verifyRacks()");
                    System.out.println(racks.get(i).getId_rack());
                    System.out.println(this.id);

                    Integer posXini = racks.get(i).getPos_x();
                    Integer posXfin = racks.get(i).getPos_x() + racks.get(i).getN_columns();
                    Integer posYini = racks.get(i).getPos_y();
                    Integer posYfin = racks.get(i).getPos_y() + 2;
                    if ((x_pos > posXfin + 1) || (x_pos < posXini - 1)) {
                        returnable = TRUE;
                    } else {
                        if ((y_pos > posYfin + 1) || (y_pos < posYini - 1)) {
                            returnable = TRUE;
                        } else {
                            returnable = FALSE;
                            break;
                        }
                    }
                }
            }
        } else if (orient == 1) {
            Integer x_pos = Integer.parseInt(pos_xField.getText());
            Integer y_pos = Integer.parseInt(pos_yField.getText());
            if (x_pos > dimensions.getLength() || y_pos > dimensions.getWidth()
                    || (y_pos + Integer.parseInt(this.numColumnsField.getText()) == dimensions.getWidth())
                    || (x_pos + 2 == dimensions.getLength())) {
                returnable = FALSE;
            } else {
                for (int i = 0; i < racks.size(); i++) {
                    if (Objects.equals(this.id, racks.get(i).getId_rack())) {
                        continue;
                    }

                    Integer posXini = racks.get(i).getPos_x();
                    Integer posXfin = racks.get(i).getPos_x() + 2;
                    Integer posYini = racks.get(i).getPos_y();
                    Integer posYfin = racks.get(i).getPos_y() + racks.get(i).getN_columns();
                    if ((x_pos > posXfin + 1) || (x_pos < posXini - 1)) {
                        returnable = TRUE;
                    } else {
                        if ((y_pos > posYfin + 1) || (y_pos < posYini - 1)) {
                            returnable = TRUE;
                        } else {
                            returnable = FALSE;
                            break;
                        }
                    }
                }
            }
        }
        return returnable;
    }

    @FXML
    private void testAction() {
        if (Integer.parseInt(pos_xField.getText()) > 0 && Integer.parseInt(pos_yField.getText()) > 0
                && Integer.parseInt(numColumnsField.getText()) > 0
                && Integer.parseInt(numFloorsField.getText()) > 0) {
            GraphicsUtils gu = new GraphicsUtils();
            ArrayList<CRack> crackListAux = new ArrayList<>();
            crackListAux = getCRacks();

            System.out.println("campis.dp1.controllers.racks.EditRackController.testAction()");
            System.out.println(crackListAux.size());
            int orient;
            if (orientationField.getValue().compareTo("Horizontal") == 0) {
                orient = 0;
            } else {
                orient = 1;
            }

            Rack rackAux = new Rack(1, Integer.parseInt(pos_xField.getText()),
                    Integer.parseInt(pos_yField.getText()),
                    Integer.parseInt(numColumnsField.getText()),
                    Integer.parseInt(numFloorsField.getText()),
                    orient);

            CRack cRackAux = new CRack(rackAux);

            //crackListAux.add(cRackAux);

            for (CRack rack : crackListAux) {
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
            int rack_y = cRackAux.getPos_y();
            int rack_x = cRackAux.getPos_x();
            int rack_length = cRackAux.getN_columns();
            if (cRackAux.getOrientation() == 0) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < rack_length; j++) {
                        real_map[i + rack_y][j + rack_x] = 2;
                    }
                }
            } else if (cRackAux.getOrientation() == 1) {
                for (int i = 0; i < rack_length; i++) {
                    for (int j = 0; j < 2; j++) {
                        real_map[i + rack_y][j + rack_x] = 2;
                    }
                }
            }

            gu.drawVisualizationMap(gc, this.y, this.x, this.real_map);

            boolean flag = verifyRacks(orient);
            boolean flagZones = verifyZones();

            if (flagZones == TRUE) {
                if (flag == TRUE) {
                    messageField.setText("Se puede editar el Rack");
                } else {
                    messageField.setText("No se puede editar el Rack, seleccione otras posiciones");
                }
            } else {
                messageField.setText("No se puede editar el Rack, no todas las zonas estan libres");
            }

        } else {
            messageField.setText("Los valores introducidos deben ser mayores a 0");
        }
    }

    @FXML
    private void clearAction() {
//        GraphicsUtils gu = new GraphicsUtils();
//        this.real_map = gu.initMap(this.y, this.x);
//        this.crackList = gu.putCRacks(wareh.getId(), real_map);
//        gu.drawVisualizationMap(gc, this.y, this.x, this.real_map);
    }

    private Warehouse getWarehouseDimensions() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class);
        System.out.println("campis.dp1.controllers.racks.EditRackController.getWarehouseDimensions()");
        System.out.println(warehouse_id);
        criteria.add(Restrictions.eq("id_warehouse", warehouse_id));

        List<Warehouse> list = criteria.list();
        Warehouse returnable = list.get(0);
        System.out.println(returnable.getId());
        session.close();
        sessionFactory.close();
        return returnable;
    }

}
