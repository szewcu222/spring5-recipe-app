package guru.springframework.controllers;

import guru.springframework.dtos.RecipeDTO;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    @InjectMocks
    ImageController imageController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(imageController)
                .build();
    }

    @Test
    void getImageForm() throws Exception {
        //given
        RecipeDTO recipe = new RecipeDTO();
        recipe.setId(1L);

        when(recipeService.getRecipeDTOByID(anyLong())).thenReturn(recipe);

        //when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService).getRecipeDTOByID(anyLong());
    }

    @Test
    void handleImagePost() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("file", "testing.txt", "text/plain",
                        "Spring Framework Guru".getBytes());

        this.mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/recipe/1/show"));

        verify(imageService).saveImageFile(anyLong(), any());
    }

    @Ignore
    @Test
    public void renderImageFromDB() throws Exception {

        //given
        RecipeDTO recipe = new RecipeDTO();
        recipe.setId(1L);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;
        for(byte primByte : s.getBytes()) {
           bytesBoxed[i++] = primByte;
        }

        recipe.setImage(bytesBoxed);

        when(recipeService.getRecipeDTOByID(anyLong())).thenReturn(recipe);
        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeImage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, responseBytes.length);

    }
}