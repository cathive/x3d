package com.cathive.x3d;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.net.URL;
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

    public X3DLoader() throws X3DException {
        try {
            this.jaxbContext = JAXBContext.newInstance(org.web3d.x3d.version_3_3.X3D.class);
        } catch (final JAXBException e) {
            throw new X3DException("Initialization of the X3D Loader failed.", e);
        }
    }

    public <T> T loadFromXml(final String location) throws X3DException {
        return this.loadFromXml(Thread.currentThread().getContextClassLoader().getResource(location));
    }

    public <T> T loadFromXml(final URL location) throws X3DException {
        try {
            final Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(location);
        } catch (final JAXBException e) {
            throw new X3DException("Error while loading X3D file from URL " + location.toExternalForm() + ".", e);
        }
    }

    public <T> T loadFromXml(final InputStream inputStream) throws X3DException {
        try {
            final Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(inputStream);
        } catch (final JAXBException e) {
            throw new X3DException("Error while loading X3D file from input stream.", e);
        }
    }

}
