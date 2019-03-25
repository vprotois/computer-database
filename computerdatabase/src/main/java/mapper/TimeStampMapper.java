package mapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimeStampMapper {
	
	private static Logger log= LoggerFactory.getLogger(TimeStampMapper.class);
	
	public static Timestamp stringToTimestamp(String stringDate){
		if(stringDate == null ||stringDate.equals("null")||"".equals(stringDate)) {
			return null;
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
			Date date;
			try {
				date = dateFormat.parse(stringDate);
			}catch(ParseException e) {
				log.error("Couldn't parse "+stringDate + " to timestamp ");
				return null;
			}
			Timestamp timeStampDate = new Timestamp(date.getTime());
			return timeStampDate;
		}
	}
	
	public static Timestamp simpleStringToTimestamp(String stringDate){
		if(stringDate == null ||stringDate.equals("null")||"".equals(stringDate)) {
			return null;
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date;
			try {
				date = dateFormat.parse(stringDate);
			}catch(ParseException e) {
				log.error("Couldn't parse "+stringDate + " to timestamp ");
				return null;
			}
			Timestamp timeStampDate = new Timestamp(date.getTime());
			return timeStampDate;
		}
	}
	
	



}
