package guru.springframework.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class ImageService {

    public void saveImageFile(Long valueOf, MultipartFile file) throws IOException {
        log.debug("Received a File");
        file.transferTo(new File("G:\\Projects\\spring5-recipe-app\\src\\main\\resources\\static\\images" + file.getOriginalFilename()));
    }
}
