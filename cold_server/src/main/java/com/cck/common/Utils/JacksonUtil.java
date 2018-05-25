/**
 * 
 */
package com.cck.common.Utils;

import org.codehaus.jackson.*;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jay
 * @since 2016年12月26日
 */
public class JacksonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	// Object序列化Json
	public static String ToJson(Object entity) throws JsonGenerationException, JsonMappingException, IOException {
		String entity2Json = objectMapper.writeValueAsString(entity);
		return entity2Json;
	}
	
	// Json反序列化Object
	@SuppressWarnings("unchecked")
	public static <E> E ToObject(String jsonString, Class<?> objectclass)
			throws JsonParseException, JsonMappingException, IOException {
		E Class = (E) objectMapper.readValue(jsonString, objectclass);
		return Class;
	}
	
	// Entity带Date序列化时间格式化
	@SuppressWarnings({ "deprecation" })
	public static String objectWithTimeFormat(Object entity)
			throws JsonGenerationException, JsonMappingException, IOException {
		java.text.DateFormat myFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		objectMapper.getSerializationConfig().setDateFormat(myFormat);
		String entity2Json = objectMapper.writeValueAsString(entity);
		return entity2Json;
	}

	// 另外一种序列化,对象转控制台输出
	public static void reseverObject(Object o) throws JsonGenerationException, JsonMappingException, IOException {
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
		jsonGenerator.writeObject(o);
	}

	// 反序列化
	@SuppressWarnings({ "unchecked" })
	public static <E> E serializerObject1(String jsonString, JavaType jt)
			throws JsonParseException, JsonMappingException, IOException {
		E Class = (E) objectMapper.readValue(jsonString, jt);
		return Class;
	}

	// 根据json字符串转化List和Map
	// 如果是Map类型
	// mapper.getTypeFactory().constructParametricType(HashMap.class,String.class,
	// Bean.class);
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	// json序列化map
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map serializerMap(String json) throws JsonParseException, JsonMappingException, IOException {
		// String json =
		// "{\"error\":0,\"data\":{\"name\":\"ABC\",\"age\":20,\"phone\":{\"home\":\"abc\",\"mobile\":\"def\"},\"friends\":[{\"name\":\"DEF\",\"phone\":{\"home\":\"hij\",\"mobile\":\"klm\"}},{\"name\":\"GHI\",\"phone\":{\"home\":\"nop\",\"mobile\":\"qrs\"}}]},\"other\":{\"nickname\":[]}}";
		Map<String, Map<String, Object>> maps = new HashMap<String, Map<String, Object>>();
		maps = objectMapper.readValue(json, Map.class);
		// System.out.println(maps.toString());
		// System.out.println(maps.get("error"));
		// List list = (List) maps.get("data").get("friends");
		// Map<String, Map<String, Object>> map = (Map<String, Map<String,
		// Object>>) list.get(0);
		// System.out.println(map.get("phone").get("home"));
		return maps;
	}

	// 慢加载，根据json内节点名称获取节点内数据
	public static JsonNode slowSerializer(String json, String nodeName) throws JsonProcessingException, IOException {
		// String test =
		// "{\"results\":[{\"objectID\":357,\"geoPoints\":[{\"x\":504604.59802246094,\"y\":305569.9150390625}]},{\"objectID\":358,\"geoPoints\":[{\"x\":504602.2680053711,\"y\":305554.43603515625}]}]}";
		JsonNode nodes = objectMapper.readTree(json); // 将Json串以树状结构读入内存
		JsonNode nodeContents = nodes.get(nodeName);// 得到results这个节点下的信息
		/*
		 * for (int i = 0; i < contents.size(); i++) //
		 * 遍历results下的信息，size()函数可以得节点所包含的的信息的个数，类似于数组的长度 {
		 * System.out.println(contents.get(i).get("objectID").getIntValue()); //
		 * 读取节点下的某个子节点的值 JsonNode geoNumber = contents.get(i).get("geoPoints");
		 * for (int j = 0; j < geoNumber.size(); j++) // 循环遍历子节点下的信息 {
		 * System.out.println( geoNumber.get(j).get("x").getDoubleValue() + " "
		 * + geoNumber.get(j).get("y").getDoubleValue()); } }
		 */
		return nodeContents;
	}
	
	public static <T> T readJson(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
	       ObjectMapper mapper = new ObjectMapper();

	       JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);

	       return mapper.readValue(jsonStr, javaType);

	}
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String test1 = "{\"objectID\":357,\"geoPoints\":[{\"x\":504604.59802246094,\"y\":305569.9150390625},{\"x\":504604.59,\"y\":305569.91}]}";
		/*
		 * testJsonClass test =
		 * JacksonUtil.serializerObject(test1,testJsonClass.class);
		 * System.out.println(test.getObjectID());
		 * JacksonUtil.reseverObjectTest(test);
		 * System.out.print(test.getGeoPoints().get(0).getX());
		 * 
		 * 
		 * String xy =
		 * "[{\"x\":504604.59802246094,\"y\":305569.9150390625},{\"x\":504604.59,\"y\":305569.91}]";
		 * JavaType jt = JacksonUtil.getCollectionType(List.class,XandY.class);
		 * List<XandY> lst = (List<XandY>)objectMapper.readValue(xy, jt); XandY
		 * xandy = lst.get(0); System.out.println(xandy.getX());
		 */
		JsonNode jnode = slowSerializer(test1, "geoPoints");
		JsonNode jnode1 = slowSerializer(test1, "");
		System.out.println(jnode1.size());

	}
}
