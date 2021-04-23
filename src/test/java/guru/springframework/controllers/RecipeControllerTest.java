package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.dtos.RecipeDTO;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void testShowById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
//                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"));
//                .andExpect(model().attributeExists("recipe"));
    }


    @Test
    void saveOrUpdate() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(2L);

        when(recipeService.saveRecipeDto(any())).thenReturn(recipeDTO);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception{
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(2L);

        when(recipeService.getRecipeDTOByID(anyLong())).thenReturn(recipeDTO);

        mockMvc.perform(get("/recipe/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"));
//                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testDeleteRecipeById() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteRecipeById(anyLong());
    }
}