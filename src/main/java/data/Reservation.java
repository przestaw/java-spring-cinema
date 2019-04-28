package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "reservation")
public class Reservation {

	@JsonProperty(value="screeningId")
	private final int screeningId;
	@JsonProperty(value="name")
	private final String name;
	@JsonProperty(value="surname")
	private final String surname;
	@JsonProperty(value="places")
	private final List<ResPlace> places;
	//List<ResPlace> places;
	 
	@JsonCreator
	public Reservation(int screeningId, String name, String surname, ArrayList<ResPlace> places) {
		this.screeningId = screeningId;
		this.name = name;
		this.surname = surname;
		this.places = places;
	}

	public Reservation(int screeningId, String name, String surname, ResPlace[] places) {
		this.screeningId = screeningId;
		this.name = name;
		this.surname = surname;
		this.places = new ArrayList<ResPlace>();
		this.places.addAll(Arrays.asList(places));
	}
	
	public Reservation(int screeningId, String name, String surname, ResPlace place) {
		this.screeningId = screeningId;
		this.name = name;
		this.surname = surname;
		this.places = new ArrayList<ResPlace>();
		this.places.add(place);
	}
	
	public int getScreeningId() {
		return screeningId;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public List<ResPlace> getPlaces() {
		return places;
	}
}
