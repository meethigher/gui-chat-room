package gui.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证是否是合法的ip地址
 * @author kitchen
 *
 */
class HostFormatException extends Exception {
	
}
public class CheckIP {
	
	/**
	 * 判断是否是合法ip
	 * @param host
	 * @return
	 */
	public static boolean isIP(String host) throws HostFormatException {
		if(host.equals("localhost")) {
			return true;
		}else {
			Pattern p=Pattern.compile("^\\d{1,3}(.\\d{1,3}){3}$");
			Matcher m=p.matcher(host);
			return m.matches();
		}
	}
}
