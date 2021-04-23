package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.dtos.RecipeDTO;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/{id}/show")
    public String showById(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeDTO());
        return "recipe/recipeform";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeDTOByID(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping
    public String saveOrUpdateRecipe(@ModelAttribute RecipeDTO recipeDto) {
        RecipeDTO savedRecipeDto = recipeService.saveRecipeDto(recipeDto);

        return "redirect:" + savedRecipeDto.getId() + "/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteRecipeId(@PathVariable String id) {
        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }

}
