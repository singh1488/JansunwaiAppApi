package in.nic.igrs.data.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Util {
	
	public static String getPcInfo() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		/*if (!StringUtils.isEmpty(request.getRemoteAddr())) {
			return request.getRemoteAddr();
		} else*/ if (!StringUtils.isEmpty(request.getHeader("REMOTE_ADDR"))) {
			return request.getHeader("REMOTE_ADDR");
		} else {
			return request.getRemoteAddr();
		}		
	}
	
	public static String getStackTraceAsString(Throwable throwable) {
		StringWriter stringWriter = new StringWriter();
		throwable.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
	
	
	public static ListContainer unauthorizedUser() {
		ListContainer lc = new ListContainer();
		List<Map<String, Object>> rows = null;
		rows = new ArrayList<Map<String, Object>>();
		Map<String, Object> hash_map = new HashMap<String, Object>(); 
		hash_map.put("status", "Unauthorized User");
		rows.add(hash_map);
		lc.setReport(rows);
		return lc;
	}
	
	
	public static List<Map<String, Object>> createMap(String key,int value) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Map<String, Object> hash_map = new HashMap<String, Object>(); 
		hash_map.put(key, value);
		rows.add(hash_map);
		return rows;
	}
	
	
	public static ListContainerStr unauthorizedUserStr() {
		ListContainerStr lc = new ListContainerStr();
		lc.setReport("Unauthorized User");
		return lc;
	}
	
	public static String removeLastChar(String s) {
		return (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));
	}
	
	public static String createOtp() {
        long otpl = (Math.round(Math.random() * 89999) + 10000);
        String otp = Long.toString(otpl);
        return otp;
    }
	
}
