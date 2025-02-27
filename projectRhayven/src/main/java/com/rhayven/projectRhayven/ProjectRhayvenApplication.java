package com.rhayven.projectRhayven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class ProjectRhayvenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectRhayvenApplication.class, args);
	}
	@GetMapping("/about-me")

	public String aboutMe(Model model){
		String fN = "Rhayven Jonas Jacalne Alano";
		model.addAttribute("fullName", fN);
		String fQ = "Always learn by doing!";
		model.addAttribute("favoriteQuote", fQ);
		String dAS = "I am a driven student from Lorma Colleges, who is not only making waves in " +
				"the field of Information Technology but also dreaming big beyond the world of tech. " +
				"While most of his peers are laser-focused on their tech careers, Rhayven has set his " +
				"sights on the skies, aiming to become a pilot.";
		model.addAttribute("descriptionAboutSelf", dAS);


		return "about_me";
	}

}
