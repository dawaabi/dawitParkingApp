package com.parkingApp;

import com.parkingApp.entities.ParkingSlot;
import com.parkingApp.repositories.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DawitParkingAppApplication implements CommandLineRunner {

	@Autowired
	private ParkingSlotRepository slotRepository;

	public static void main(String[] args) {
		SpringApplication.run(DawitParkingAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 1; i <= 10; i++) {
			slotRepository.save(new ParkingSlot(i <= 5 ? "compact" : "large", "free", null));
		}
	}
}
