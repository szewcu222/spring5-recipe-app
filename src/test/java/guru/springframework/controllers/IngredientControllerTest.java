package guru.springframework.controllers;

import guru.springframework.dtos.RecipeDTO;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.RecipeServiceImpl;
import guru.springframework.services.UOMService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UOMService uomService;

    IngredientController ingredientController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientController = new IngredientController(recipeService, ingredientService, uomService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void listOfIngredientsRepo() throws Exception {
        //given recipe opject
        RecipeDTO recipe = new RecipeDTO();
        //when perform get, check if staus ok, view and attribute
        mockMvc.perform(get("/recipe/1/ingredientsRepo"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
        //then cerify if called once
        verify(ingredientService).getIngredientsDTOByRecipeId(anyLong());
    }


    @Test
    void listOfIngredients() throws Exception {
        //given
        RecipeDTO recipeDTO = new RecipeDTO();
        when(recipeService.getRecipeDTOByID(anyLong())).thenReturn(recipeDTO);
        //when  perform get on url. Expect status ok and view
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
        //then
        verify(recipeService, times(1)).getRecipeDTOByID(anyLong());
    }


}