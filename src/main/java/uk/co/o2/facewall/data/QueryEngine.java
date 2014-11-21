package uk.co.o2.facewall.data;

import java.util.Iterator;
import java.util.Map;

public interface QueryEngine {

    Iterator<Map<String,Object>> query(String query, Map<String,Object> params);

}
