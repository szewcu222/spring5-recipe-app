package guru.springframework.controllers;

import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
//import org.springframework.web.

@Controller
@RequestMapping("/recipe")
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/{recipeId}/image")
    public String showUploadForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeDTOByID(Long.valueOf(recipeId)));

        return "recipe/imageuploadform";

    }

    @PostMapping("/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) throws IOException {

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" + id + "/show";

    }
}
