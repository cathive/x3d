package com.cathive.x3d.viewer;

import com.cathive.x3d.X3DLoader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.MeshView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.web3d.x3d.version_3_3.Meta;
import org.web3d.x3d.version_3_3.X3D;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Benjamin P. Jung
 */
public final class X3DViewer extends BorderPane implements Initializable {

    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(X3DViewer.class.getName());

    private final BooleanProperty toolBarVisible = new SimpleBooleanProperty(this, "toolBarVisible", true);
    public boolean isToolBarVisible() { return this.toolBarVisible.get(); }
    public void setToolBarVisible(final boolean toolBarVisible) { this.toolBarVisible.set(toolBarVisible); }
    public BooleanProperty toolBarVisibleProperty() { return this.toolBarVisible; }

    private ObjectProperty<File> x3dFile = new SimpleObjectProperty<>(this, "x3dFile");
    public File getX3dFile() { return this.x3dFile.get(); }
    public void setX3dFile(final File x3dFile) { this.x3dFile.set(x3dFile); }
    public ObjectProperty<File> x3dFileProperty() { return this.x3dFile; }

    private ObjectProperty<X3D> x3dObject = new SimpleObjectProperty<>(this, "x3dObject");
    public X3D getX3dObject() { return this.x3dObject.get(); }
    public void setX3dObject(final X3D x3dObject) { this.x3dObject.set(x3dObject); }
    public ObjectProperty<X3D> x3dObjectProperty() { return this.x3dObject; }

    private X3DLoader x3dLoader;

    @FXML private ToolBar toolBar;

    @FXML private TableView<Meta> x3dHeadMetaTable;
    @FXML private TableColumn<Meta, String> x3dHeadMetaNameTableColumn;
    @FXML private TableColumn<Meta, String> x3dHeadMetaContentTableColumn;

    @FXML
    private Group scene;

    public X3DViewer() {

        super();

        final URL location = getClass().getResource(getClass().getSimpleName() + ".fxml");
        final ResourceBundle messages = Messages.getResourceBundle();

        final FXMLLoader fxmlLoader = new FXMLLoader(location, messages);
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> aClass) {
                return X3DViewer.this;
            }
        });
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (final IOException e) {
            throw new RuntimeException("An error occurred during initialization of the X3D Viewer component.", e);
        }
    }

    @Override
    public void initialize(final URL location, final ResourceBundle messages) {

        // Initialize the X3D Loader class.
        this.x3dLoader = new X3DLoader();

        // Perform some bindings.
        this.toolBar.visibleProperty().bind(this.toolBarVisible);

        this.x3dHeadMetaNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.x3dHeadMetaContentTableColumn.setCellValueFactory(new PropertyValueFactory<>("content"));

        this.x3dObject.addListener((observableValue, oldValue, newValue) -> {
            final ObservableList<Meta> metaItems = this.x3dHeadMetaTable.getItems();
            metaItems.clear();
            metaItems.addAll(newValue.getHead().getMeta());
        });


    }

    @FXML
    public void openFile(final ActionEvent event) {
        final File _x3dFile = new FileChooser().showOpenDialog(this.getScene().getWindow());
        if (_x3dFile != null) {
            try {
               final X3D _x3dObject = x3dLoader.loadFromXml(new FileInputStream(_x3dFile));
                this.setX3dFile(_x3dFile);
                this.setX3dObject(_x3dObject);
            } catch (final FileNotFoundException e) {
                LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
            }
        }
    }

}
