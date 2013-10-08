package com.cathive.x3d;

import javafx.scene.shape.Mesh;
import org.web3d.x3d.version_3_3.X3D;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This class provides methods to load X3D files.
 * @author Benjamin P. Jung
 */
public class X3DLoader {

    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(X3DLoader.class.getName());

    /** The JAXB context to be used. */
    final JAXBContext jaxbContext;

    /**
     * Constructs a new X3D loader instance.
     * @throws X3DException
     */
    public X3DLoader() throws X3DException {
        final Class[] classesToBeBound = new Class[] { org.web3d.x3d.version_3_3.X3D.class };
        final Map<String, Object> properties = new HashMap<>(4);
        try {
            this.jaxbContext = JAXBContext.newInstance(classesToBeBound, properties);
        } catch (final JAXBException e) {
            throw new X3DException("Initialization of the X3D Loader failed.", e);
        }
    }

    public X3D loadFromXml(final String location) throws X3DException {
        return this.loadFromXml(Thread.currentThread().getContextClassLoader().getResource(location));
    }

    public X3D loadFromXml(final URL location) throws X3DException {
        try {
            final Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
            return (X3D) unmarshaller.unmarshal(location);
        } catch (final JAXBException e) {
            throw new X3DException("Error while loading X3D file from URL " + location.toExternalForm() + ".", e);
        }
    }

    public X3D loadFromXml(final InputStream inputStream) throws X3DException {
        try {
            final Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
            return (X3D) unmarshaller.unmarshal(inputStream);
        } catch (final JAXBException e) {
            throw new X3DException("Error while loading X3D file from input stream.", e);
        }
    }

    public <T extends Mesh> T loadMeshFromXml(final InputStream inputStream) throws X3DException {
        final X3D x3d = loadFromXml(inputStream);
        return null;
    }

}
