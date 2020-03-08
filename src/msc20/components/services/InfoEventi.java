package msc20.components.services;

import java.util.HashMap;
import java.util.Map;

public class InfoEventi {

	public int getMaxPartecipantOf(String event) {
		return events_info.get(event);
	}

	public static InfoEventi getIstance() {

		if (infoEventi == null)
			infoEventi = new InfoEventi();
		return infoEventi;
	}

	private InfoEventi() {
		events_info = new HashMap<>();
		init();

	}

	private void init() {
		events_info.put("Bandy", 22);
		events_info.put("Baseball", 22);
		events_info.put("Beachsoccer", 22);
		events_info.put("Beachvolley", 22);
		events_info.put("Bob", 22);
		events_info.put("Bocce", 22);
		events_info.put("Broomball", 22);
		events_info.put("Calcio", 22);
		events_info.put("Calciotto", 16);
		events_info.put("Calcetto", 10);
		events_info.put("Calciogaelico", 22);
		events_info.put("Canoapolo", 22);
		events_info.put("Cricket", 22);
		events_info.put("Croquet", 22);
		events_info.put("Curling", 22);
		events_info.put("Floorball", 22);
		events_info.put("Footballamericano", 22);
		events_info.put("Footballaustraliano", 22);
		events_info.put("Footballcanadese", 22);
		events_info.put("Footballsala", 22);
		events_info.put("Ginnastica", 22);
		events_info.put("Ginnasticaartistica", 22);
		events_info.put("Ginnasticaritmica", 22);
		events_info.put("Giocodiguadagno-campo", 22);
		events_info.put("Goalball", 22);
		events_info.put("Hockey", 22);
		events_info.put("Hurling", 22);
		events_info.put("Korfball", 22);
		events_info.put("Lacrosse", 22);
		events_info.put("Netball", 22);
		events_info.put("Nuotosincronizzato", 22);
		events_info.put("Paintball", 22);
		events_info.put("Pallabasca", 22);
		events_info.put("Pallacanestro", 22);
		events_info.put("Pallamano", 22);
		events_info.put("Pallanuoto", 22);
		events_info.put("Pallapugno", 22);
		events_info.put("Pallavolo", 12);
		events_info.put("Pallonecolbracciale", 22);
		events_info.put("Rollerderby", 22);
		events_info.put("Rugby", 22);
		events_info.put("Slittino", 22);
		events_info.put("Softball", 22);
		events_info.put("Tamburello", 22);
		events_info.put("Tchoukball", 22);
		events_info.put("Tennis da spiaggia", 22);
		events_info.put("Touchrugby", 22);
		events_info.put("Trampolinoelastico", 22);
		events_info.put("Ultimate", 22);
		events_info.put("Tennis", 2);
	}

	public Map<String, Integer> getRolesInfoForEvent(String typeOfStructure) {
		HashMap<String, Integer> roles = new HashMap<>();
		switch (typeOfStructure) {

		case "Calcio":
			roles.put("POR", 2);
			roles.put("DIF", 8);
			roles.put("CEN", 8);
			roles.put("ATT", 4);
			break;
		case "Calciotto":
			roles.put("POR", 2);
			roles.put("DIF", 6);
			roles.put("CEN", 6);
			roles.put("ATT", 2);
			break;
		case "Calcetto":
			roles.put("POR", 2);
			roles.put("DIF", 4);
			roles.put("CEN", 2);
			roles.put("ATT", 2);
			break;
		case "Tennis":
			roles.put("TEN", 2);
			break;
		default:
			break;
		}
		return roles;
		
	}

	private static InfoEventi infoEventi;
	private HashMap<String, Integer> events_info;
	public int getRoleLimit(String typeOfStructure, String role) {
		return getRolesInfoForEvent(typeOfStructure).get(role);
	}

}
