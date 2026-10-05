/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.utils;

import pl.edu.wat.wcy.cop.app.client.utils.CopLogger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
// Loads jaxb.


public class JaxbLoader {
    public static boolean saveObject(URL url, Object objectToSave, Class<?>... objectsClasses) {
        File file = new File(url.toString());
        return saveObject(file, objectToSave, objectsClasses);
    }

    public static boolean saveObject(File file, Object objectToSave, Class<?>... objectsClasses) {
        boolean result = true;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(objectsClasses);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(JaxbLoader.class, file);
        } catch (JAXBException e) {
            CopLogger.getInstance().error(objectToSave,e.getMessage());
            result = false;
        }
        return result;
    }

    public static boolean saveObject(BufferedWriter writer, Object objectToSave, Class<?>... objectsClasses) {
        boolean result = true;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(objectsClasses);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(objectToSave, writer);
            writer.flush();
            writer.close();
        } catch (JAXBException | IOException e) {
            CopLogger.getInstance().error(JaxbLoader.class, e.getMessage());
            result = false;
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T loadObject(URL url, Class<T> rootClass, Class<?>... objectsClasses) {
        if (url != null)
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(objectsClasses);
                Unmarshaller jaxbUnmarshaller;
                jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                return (T) jaxbUnmarshaller.unmarshal(url);
            } catch (JAXBException e) {
                // TODO Auto-generated catch block
                CopLogger.getInstance().error(JaxbLoader.class,e.getMessage());
            }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T loadObject(File file, Class<T> collectionGenericClass, Class<?>... objectsClasses) {

        try {
            return loadObject(file.toURL(), collectionGenericClass, objectsClasses);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T loadObject(BufferedReader bufferedReader, Class<T> collectionGenericClass,
                                   Class<?>... objectsClasses) {
        if (bufferedReader != null)
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(objectsClasses);
                Unmarshaller jaxbUnmarshaller;
                jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                return (T) jaxbUnmarshaller.unmarshal(bufferedReader);
            } catch (JAXBException e) {
                CopLogger.getInstance().error(JaxbLoader.class, e.getMessage());
            }
        return null;
    }

    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
