package gui.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��֤�Ƿ��ǺϷ���ip��ַ
 * @author kitchen
 *
 */
class HostFormatException extends Exception {
	
}
public class CheckIP {
	
	/**
	 * �ж��Ƿ��ǺϷ�ip
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
