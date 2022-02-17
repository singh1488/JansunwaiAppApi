package in.nic.igrs.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nic.igrs.data.dao.PublicAppDao;
import in.nic.igrs.data.util.ListContainer;
import in.nic.igrs.data.util.ParamBuilderWithNameFun;
import in.nic.igrs.data.util.SendMail;
import in.nic.igrs.data.util.Util;
import in.nic.igrs.data.validation.ValidationFailedException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Service
public class PublicAppService {
	
	@Autowired
	PublicAppDao publicAppDao;
	
	private Cache<String, Long> cacheMobTimeU;
	
	@PostConstruct
	public void postConstruct() {
		cacheMobTimeU = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.MINUTES).build();		
	}

	public ListContainer getAppDetails(HttpServletRequest request) {
		ListContainer lc = new ListContainer();
		try {
			ParamBuilderWithNameFun pb = new ParamBuilderWithNameFun("udf_online_entry_checkmobileapp");
			lc.setReport(publicAppDao.executeQuery("udf_online_entry_checkmobileapp()", pb.getParamList()));
		} catch (Exception e) {
		}
		return lc;
	}

	public ListContainer getUserDetails(String mobileNo,String langauge, HttpServletRequest request) {
		ListContainer lc = new ListContainer();
		
		ParamBuilderWithNameFun pb = new ParamBuilderWithNameFun("udf_check_mobile_app_user");
		pb.addParam("p_mobile_no", mobileNo);
		List<Map<String,Object>> li = publicAppDao.executeQuery("udf_check_mobile_app_user(?)", pb.getParamList());
		
		List<Map<String,Object>> result = new ArrayList<>() ;
		
		if(li != null && li.size() > 0) {
			Map<String,Object> map = new HashMap<>();
			map.put("r_user_id", Long.parseLong(li.get(0).get("r_user_id").toString()));
			map.put("r_password", li.get(0).get("r_password").toString());
			map.put("r_user_name", li.get(0).get("r_user_name").toString());
			map.put("r_mobile_no", li.get(0).get("r_mobile_no").toString());
			map.put("r_email_id", li.get(0).get("r_email_id").toString());
			map.put("r_is_m_verify", Integer.parseInt(li.get(0).get("r_is_m_verify").toString()));
			map.put("r_is_e_verify", Integer.parseInt(li.get(0).get("r_is_e_verify").toString()));
			map.put("otp", "0");
			map.put("status", "NOTP");	
			result.add(map);		
			lc.setReport(result); 
		}else {
			String otp = sendOtp(mobileNo,langauge,"");
			Map<String,Object> map = new HashMap<>();
			map.put("r_user_id", 0);
			map.put("r_password", "");
			map.put("r_user_name", "");
			map.put("r_mobile_no", "");
			map.put("r_email_id", "");
			map.put("r_is_m_verify", 0);
			map.put("r_is_e_verify", 0);
			map.put("otp", otp);
			map.put("status", "YOTP");		
			result.add(map);
			lc.setReport(result);  
		}
		return lc;
	}
	
	public synchronized String sendOtp(String mobileNumber,String langauge,String templateId) {
		long currentTime = System.currentTimeMillis();

		String message = "";
		String otp = "";

		
			Long lastMessageSentTime = cacheMobTimeU.getIfPresent(mobileNumber);
			if (lastMessageSentTime != null) {
				if ((currentTime - lastMessageSentTime < 3 * 60 * 1000)) {
					throw new ValidationFailedException("Unable to send OTP SMS at the moment. Please try later.");
				}
			}

			otp = Util.createOtp();

			if (langauge.equals("hi")) {
				message = "The one time password otp for registration on Jansunwai-up App is " + otp;
			} else {
				message = "The one time password otp for registration on Jansunwai-up App is " + otp;
			}

			templateId = "1107161008868192565";
			
		
		try {						
			int statusCode = SendMail.sendSms(mobileNumber, message, templateId);
			if (statusCode == 200) {
				cacheMobTimeU.put(mobileNumber.trim(), currentTime);													
				return "Success-" + otp;
			}
			
		} catch (Exception ex) {
			return "NA";
		}
		
		return "NA";
	}
}
