package guru.springframework.services;

import guru.springframework.dtos.RecipeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class ImageService {

    private final RecipeService recipeService;

    public ImageService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    public void saveImageFile(Long recipeId, MultipartFile file) throws IOException {
        RecipeDTO recipe = recipeService.getRecipeDTOByID(recipeId);

        try {
            Byte[] bytes = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b :file.getBytes()){
                bytes[i++] = b;
            }

            recipe.setImage(bytes);

            recipeService.saveRecipeDto(recipe);
        } catch (IOException e) {
            log.error("SaveImageFileError");
            e.printStackTrace();
        }

        log.debug("Received a File");
        file.transferTo(new File("G:\\Projects\\spring5-recipe-app\\src\\main\\resources\\static\\images" + file.getOriginalFilename()));
    }
}
