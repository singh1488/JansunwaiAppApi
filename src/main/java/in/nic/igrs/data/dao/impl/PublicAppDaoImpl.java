package in.nic.igrs.data.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.nic.igrs.data.dao.PublicAppDao;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@Transactional
public class PublicAppDaoImpl implements PublicAppDao {

	 @Autowired
	 @Qualifier("jdbcTemplateIGRS")
	 private JdbcTemplate jdbctemplateIGRS;

	@Override
	public List<Map<String, Object>> executeQuery(String fun, Map<String, Object> map) {
		List<Map<String, Object>> rows = null;

		try {
			rows = jdbctemplateIGRS.queryForList("select * from " + fun, new ArrayList<Object>(map.values()).toArray());
		} catch (Exception e) {
			log.error(" @@@@@ Exception in SDCImpl Replica ", e);
		}

		return rows;
	}

}
