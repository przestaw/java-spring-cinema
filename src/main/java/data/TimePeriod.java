package data;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "time_period")
public class TimePeriod {
	@JsonProperty(value="begin")
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(pattern = "yyyy-MM-dd-HH:mm")
	private final LocalDateTime begin;
	@JsonProperty(value="end")
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(pattern = "yyyy-MM-dd-HH:mm")
	private final LocalDateTime end;
	
	public TimePeriod(LocalDateTime begin, LocalDateTime end) {
		this.begin = begin;
		this.end = end;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public LocalDateTime getEnd() {
		return end;
	}
	
}
