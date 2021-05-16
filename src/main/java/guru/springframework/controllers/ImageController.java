package guru.springframework.controllers;

import guru.springframework.dtos.RecipeDTO;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @GetMapping("/{id}/recipeImage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeDTO recipe = recipeService.getRecipeDTOByID(Long.valueOf(id));

        byte[] byteArray = new byte[recipe.getImage().length];

        int i = 0;
        for (Byte wrappedByte : recipe.getImage()) {
            byteArray[i++] = wrappedByte; //auto unboxing
        }

        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

}
