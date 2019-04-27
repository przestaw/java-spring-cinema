package data;

import java.time.LocalDateTime;

public class TimePeriod {
	
	private final LocalDateTime begin;
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
