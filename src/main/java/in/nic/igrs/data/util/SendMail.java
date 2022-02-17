package in.nic.igrs.data.util;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SendMail {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMail.class);

	public static String sendEmail(String to, String subject, String text) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("api-key", "$2a$10$opvhZUI2k4OmyF1BJjSxb.6QRQ8UvyDvGDOqdXdqe0ZqzTc4MrHh2");

			ResponseEntity<String> res = (new RestTemplate()).exchange(
					"http://10.247.146.5:80/sms-igrs-data-service-mobile/send-email?emailId=" + to + "&message=" + text,
					HttpMethod.GET, new HttpEntity<Object>((MultiValueMap<String, String>) headers), String.class,
					new Object[0]);

			int resco = res.getStatusCodeValue();

			if (res.getStatusCodeValue() == 200)
				return (String) res.getBody();
		} catch (Exception e) {
			return "NA";
		}
		return "NA";
	}

	public static int sendSms(String sToPhoneNo, String sMessage, String tamplateId) {

		try {

			/*
			 * HttpHeaders headers = new HttpHeaders(); headers.add("api-key",
			 * "$2a$10$opvhZUI2k4OmyF1BJjSxb.6QRQ8UvyDvGDOqdXdqe0ZqzTc4MrHh2");
			 * ResponseEntity<String> response = new RestTemplate().exchange(
			 * "http://10.247.146.5:80/igrs-data-service-mobile-tamId/send-sms-only?mobileNumber="
			 * + sToPhoneNo + "&tamplateId=" + tamplateId + "&message=" +
			 * URLEncoder.encode(sMessage, "UTF-8"), HttpMethod.GET, new
			 * HttpEntity<>(headers), String.class);
			 * 
			 * if (response != null && response.getBody() != null) {
			 * LOGGER.info("SMS service Response : " + response.getBody()); JsonNode node =
			 * new ObjectMapper().readTree(response.getBody()); JsonNode resultNode =
			 * node.findValue("Result");
			 * 
			 * if (resultNode != null && resultNode.asText() != null &&
			 * resultNode.asText().equals("1")) { return 200; } }
			 */

			HttpHeaders headers = new HttpHeaders();
			headers.add("api-key", "$2a$10$opvhZUI2k4OmyF1BJjSxb.6QRQ8UvyDvGDOqdXdqe0ZqzTc4MrHh2");
			ResponseEntity<String> response = new RestTemplate().exchange(
					"https://jansunwai.up.nic.in/sms-igrs-data-service-mobile/send-ack-db?mobileNmber=" + sToPhoneNo
							+ "&message=" + URLEncoder.encode(sMessage, "UTF-8") + "&templateId=" + tamplateId,
					HttpMethod.GET, new HttpEntity<>(headers), String.class);

			if (response != null && response.getBody() != null) {
				LOGGER.info("SMS service Response : " + response.getBody());
				//JsonNode node = new ObjectMapper().readTree(response.getBody());
				//JsonNode resultNode = node.findValue("Result");

				if (response.getBody() != null && response.getBody() != null && response.getBody().equals("true")) {
					return 200;
				}
			}

		} catch (Exception e) {
			return -200;
		}

		return -200;
	}

}
