package sk.upjs.Engine;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

/**
* Created by Viliam on 21.3.2015.
*/


public class JSONCollectionMapper {
    //private final URL url;




//    public <T extends Collection<E>,E> T parseToCollection(String url, Class<T> collectionClass, Class<E> itemClass) throws IOException{
//        return parseToCollection(new URL(url), collectionClass, itemClass);
//    }

    public <T extends Collection<E>,E> T parseToCollection(String jsonText, Class<T> collectionClass, Class<E> itemClass) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.getTypeFactory();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        T data = mapper.readValue(jsonText, mapper.getTypeFactory().constructCollectionType(collectionClass, itemClass));
       // data = mapper.readVal
        //    Object data = mapper.readValue(url, Object.class);
        return data;
    }

    public <T extends Collection<E>,E> T parseToCollection(URL url, Class<T> collectionClass, Class<E> itemClass) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.getTypeFactory();
        T data = mapper.readValue(url, mapper.getTypeFactory().constructCollectionType(collectionClass, itemClass));

    //    Object data = mapper.readValue(url, Object.class);
        return data;

    }

    public <T extends Collection<E>,E> T parseToCollection(File file, Class<T> collectionClass, Class<E> itemClass) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.getTypeFactory();

        T data = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(collectionClass, itemClass));
        //    Object data = mapper.readValue(url, Object.class);
        return data;

    }
}
