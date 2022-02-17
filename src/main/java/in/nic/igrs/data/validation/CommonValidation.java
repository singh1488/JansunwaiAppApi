package in.nic.igrs.data.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidation {
	
	
	public static boolean isValidMobile(String s) { 
	    // 1) Begins with 0 or 91 
	    // 2) Then contains 7 or 8 or 9. 
	    // 3) Then contains 9 digits 
	    Pattern p = Pattern.compile("(0/91)?[5-9][0-9]{9}"); 
	    Matcher m = p.matcher(s); 
	    return (m.find() && m.group().equals(s)); 
	} 
	
	public static boolean isValidEmail(String email) { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null || email.isEmpty() || email.equals("")) 
            return true; 
        return pat.matcher(email).matches(); 
    } 
	
	
	public static boolean isValidComplaintCode(Long s) { 
	    if(s != null) {
	    	int length = String.valueOf(s).length();
	    	if(s == 0) {
	    		return true;
	    	}else if(length == 14) {
	    		return true;
	    	}else {
	    		return false;
	    	}
	    }else {
	    	return false;
	    }
	} 
	
	
	public static boolean checkSpecialChar(String value) {
		
		Pattern special = Pattern.compile("[!@#$%^&*/'\"\\\"\"()_+=<>?{}\\[\\]~-]");
		boolean result = false;
		
		if(value != null) {
			if (!value.isEmpty()) {
				Matcher hasSpecial = special.matcher(value);
				if (hasSpecial.find()) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		return result;
		
	}

	public static String replaceSpecialCharForAdd(String data) {
		String result = "";
		if (data != null) {
			result = data.replaceAll("[$&+,:;=?@#|'<>.-^*()%!/\"][^A-Z a-z 1-9]", "");
		}
		return result;
	}
	
}
