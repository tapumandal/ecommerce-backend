package com.tapumandal.ecommerce.domain.image;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.entity.Product;
import com.tapumandal.ecommerce.service.FileStorageService;
import com.tapumandal.ecommerce.util.CommonResponseArray;
import com.tapumandal.ecommerce.util.CommonResponseSingle;
import com.tapumandal.ecommerce.util.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ImageController extends ControllerHelper<Image> {


    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    ImageService imageService;

    @GetMapping("/public/images/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        if(resource == null) {
            return null;
        }
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping(path = "/public/images/{name}")
    public CommonResponseSingle<Image> deleteImage(@PathVariable("name") String imageName, HttpServletRequest request) {

        storeUserDetails(request);

        if (imageService.deleteImageByName(imageName)) {
            return response(true, HttpStatus.OK, "Image by name " + imageName + " is deleted", (Image) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Image not found or deleted", (Image) null);
        }
    }

}