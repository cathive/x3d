package com.cathive.x3d;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import org.web3d.x3d.version_3_3.Head;
import org.web3d.x3d.version_3_3.Meta;
import org.web3d.x3d.version_3_3.X3D;

import java.util.List;

/**
 * @author Benjamin P. Jung
 */
@Test
public class X3DLoaderTest {

    @Test(description = "Test the X3D model loader using the 'Hello, World!' example provided on the X3D websites.")
    public void testModelLoader() {

        // Loads the "Hello World" scene provided as example on the X3D websites.
        final X3DLoader loader = new X3DLoader();
        final X3D x3d = loader.loadFromXml("HelloWorld.x3d");

        assertNotNull(x3d);
        assertEquals(x3d.getVersion(), "3.3");

        final Head head = x3d.getHead();
        assertNotNull(head);

    }

}
