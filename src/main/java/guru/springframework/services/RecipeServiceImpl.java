package guru.springframework.services;

import guru.springframework.dtos.RecipeDTO;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import guru.springframework.mappers.CycleAvoidingMappingContext;
import guru.springframework.mappers.RecipeMapper;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
//        Set<Recipe> recByDiffAndCookTime = recipeRepository.findRecipesByDifficultyOrCookTime(Difficulty.EASY, 0);
//
//        System.out.println("findRecipeByNoteIn");
//        Set<Recipe> allByCategoriesId = recipeRepository.findByCategoriesId(1L);
//        Set<Recipe> allByIgredientId = recipeRepository.findRecipesByIngredientsId(1L);

        log.info("---------------------------");
        log.debug("getRecipes------------------------------");
        return recipes;
    }

    @Override
    public Set<RecipeDTO> getRecipesDTO() {
        Set<Recipe> recipes = this.getRecipes();
        Set<RecipeDTO> recipesDTO = new HashSet<>();
        recipes.forEach(r -> recipesDTO.add(RecipeMapper.MAPPER.recipeToDto(r, new CycleAvoidingMappingContext())));

        return recipesDTO;
    }
    
    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findRecipeById(id).orElse(null);
    }

    @Override
    @Transactional
    public RecipeDTO getRecipeDTOByID(Long id) {
        return RecipeMapper.MAPPER.recipeToDto(getRecipeById(id), new CycleAvoidingMappingContext());
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    @Transactional
    public RecipeDTO saveRecipeDto(RecipeDTO recipeDto) {
        Recipe savedRecipe = saveRecipe(RecipeMapper.MAPPER.dtoToRecipe(recipeDto, new CycleAvoidingMappingContext()));
        log.debug("Saved recipe: " + savedRecipe.getId());
        return RecipeMapper.MAPPER.recipeToDto(savedRecipe, new CycleAvoidingMappingContext());
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }



}
