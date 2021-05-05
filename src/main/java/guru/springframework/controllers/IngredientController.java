package guru.springframework.controllers;

import guru.springframework.dtos.IngredientDTO;
import guru.springframework.dtos.RecipeDTO;
import guru.springframework.dtos.UnitOfMeasureDTO;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UOMService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/recipe/{recipeId}/ingredient")
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UOMService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UOMService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/all")
    public String listOfIngredients(@PathVariable String recipeId, Model model) {

        RecipeDTO recipe = recipeService.getRecipeDTOByID(Long.valueOf(recipeId));
        recipe.setIngredients(recipe.getIngredients().stream()
                .sorted(Comparator.comparing(IngredientDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new))
        );
        model.addAttribute("recipe", recipe);

        return "recipe/ingredient/list";
    }

    @GetMapping("/ingredientsRepo")
    public String listOfIngredientsRepo(@PathVariable String recipeId, Model model) {
        RecipeDTO recipe = new RecipeDTO();
        Set<IngredientDTO> ingredients = ingredientService.getIngredientsDTOByRecipeId(Long.valueOf(recipeId));
        recipe.setId(Long.valueOf(recipeId));
        recipe.setIngredients(ingredients);

        model.addAttribute("recipe", recipe);

        return "recipe/ingredient/list";

    }

    @GetMapping("/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        String ss;
        model.addAttribute("ingredient" , ingredientService.getIngredientDTOForRecipe(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";
    }

    @GetMapping("/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model ) {
        model.addAttribute("ingredient" , ingredientService.getIngredientDTOForRecipe(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", uomService.getAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {
        RecipeDTO recipeDTO = recipeService.getRecipeDTOByID(Long.valueOf(recipeId));

        IngredientDTO ingredient = new IngredientDTO();
        ingredient.setRecipe(recipeDTO);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("uomList", uomService.getAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping()
    public String saveOrUpdateIngredient(@ModelAttribute IngredientDTO ingredientDTO, @PathVariable String recipeId) {
        String ss = "";
        ingredientDTO.setRecipe(recipeService.getRecipeDTOByID(Long.valueOf(recipeId)));
        IngredientDTO savedIngredientDto = ingredientService.saveIngredientDto(ingredientDTO);

        return "redirect:/recipe/" + savedIngredientDto.getRecipe().getId() + "/ingredient/" + savedIngredientDto.getId() + "/show";
    }

    @GetMapping("/{ingredientId}/delete")
    public ModelAndView deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
        ingredientService.deleteIngredientById(Long.valueOf(ingredientId));

        return new ModelAndView("redirect:/recipe/" + recipeId + "/ingredient/all");
    }

}
