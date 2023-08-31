package theodo.panier.presentation.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import theodo.panier.Interface.Generator;
import theodo.panier.Strategy.Strategies;
import theodo.panier.Strategy.JSP.JSPStrategy;
import theodo.panier.Strategy.Thymeleaf.ThymeleafStrategy;

@RestController
@RequiredArgsConstructor
public class GenerateHtmlController {
    private final ThymeleafStrategy thymeleafStrategy;
    
	@GetMapping("/generate")
	public String generateHtml(@RequestBody DocumentDto documentDto) throws IOException {
        Generator generator = createHtmlGenerator(documentDto.strategy);
        File file = new File("./src/main/resources/index.html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String htmlGenerated = generator.generateHtml(documentDto.documentParameter);
        writer.write(htmlGenerated);
        
        writer.close();
		return htmlGenerated;
	}

    private Generator createHtmlGenerator(Strategies strategy){
        return switch(strategy) {
            case THYMELEAF -> thymeleafStrategy;
            case JSP -> new JSPStrategy();
        };
    }
}
