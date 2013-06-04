package com.cathive.x3d;

import org.web3d.x3d.Scene;
import org.web3d.x3d.X3D;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

/**
 * @author Benjamin P. Jung
 */
public class X3DLoader {

    final JAXBContext jaxbContext;

    public X3DLoader() throws X3DException {
        try {
            this.jaxbContext = JAXBContext.newInstance(Scene.class);
        } catch (final JAXBException e) {
            throw new X3DException("Initialization of the X3D Loader failed.", e);
        }
    }

    public X3D load(final String location) throws X3DException {
        try {
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final URL url = Thread.currentThread().getContextClassLoader().getResource(location);
            return (X3D) unmarshaller.unmarshal(url);
        } catch (final JAXBException e) {
            throw new X3DException("Error while loading X3D file: " + location, e);
        }
    }
}
