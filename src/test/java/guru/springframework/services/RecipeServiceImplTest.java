package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
        HashSet<Recipe> recipesData = new HashSet<>(Collections.singletonList(new Recipe()));
        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();

    }

    @Test
    void getRecipeById() {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findRecipeById(anyLong())).thenReturn(recipeOptional);
        // when
        Recipe recipeReturned = recipeService.getRecipeById(1L);
        // then
        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findRecipeById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        Long idToDelete = 2L;

        //when
        recipeService.deleteRecipeById(idToDelete);

        // no 'when' since method has void to return
        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}