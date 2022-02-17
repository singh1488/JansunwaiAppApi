package in.nic.igrs.data.dao;

import java.util.List;
import java.util.Map;

public interface PublicAppDao {

	 List<Map<String,Object>> executeQuery (String fun, Map<String, Object> map);

}
