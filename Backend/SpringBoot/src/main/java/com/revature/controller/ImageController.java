package com.revature.controller;

import com.revature.config.Configuration;
import com.revature.models.JSONResponse;
import com.revature.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zimi Li
 */

@RestController("ImageController")
@RequestMapping("api")
@CrossOrigin(value = {"http://localhost:4200", "http://18.119.105.113:8080", "http://18.119.105.113:80"}, allowCredentials = "true")
public class ImageController {
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("upload")
    public JSONResponse upload(@RequestParam MultipartFile file) {
        String url = "";
        try {
            url = imageService.upload(file.getInputStream(), file.getSize());
        } catch (Exception e) {
            Configuration.LOGGER.error(e, e.fillInStackTrace());
            return new JSONResponse(false, "Upload Failed", null);
        }
        return new JSONResponse(true, "Upload succeed", url);
    }
}
