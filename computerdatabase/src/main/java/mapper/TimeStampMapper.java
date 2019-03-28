package mapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimeStampMapper {
	
	private static Logger log= LoggerFactory.getLogger(TimeStampMapper.class);
	
	public static Optional<Timestamp> stringToTimestamp(String stringDate){
		if(stringDate == null ||stringDate.equals("null")||"".equals(stringDate)) {
			return Optional.empty();
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
			Date date;
			try {
				date = dateFormat.parse(stringDate);
			}catch(ParseException e) {
				log.error("Couldn't parse "+stringDate + " to timestamp ");
				return Optional.empty();
			}
			Timestamp timeStampDate = new Timestamp(date.getTime());
			return Optional.of(timeStampDate);
		}
	}
	
	public static Optional<Timestamp> simpleStringToTimestamp(String stringDate){
		if(stringDate == null ||stringDate.equals("null")||"".equals(stringDate)) {
			return Optional.empty();
		} else {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(("dd/MM/yyyy"));
			LocalDate date;
			try {
			date = LocalDate.parse(stringDate, dateFormat);
			}catch(DateTimeParseException e){
				log.error("Couldn't parse "+stringDate + " to timestamp ");
				return Optional.empty();
			}
			if(!dateFormat.format(date).equals(stringDate)) {
				return Optional.empty();
			}
			Timestamp timeStampDate = Timestamp.valueOf(date.atStartOfDay());
			return Optional.of(timeStampDate);
		}
	}
	
	



}
