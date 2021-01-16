package com.tapumandal.ecommerce.util;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.entity.ImageModel;
import com.tapumandal.ecommerce.service.FileStorageService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceUtil {


    @Autowired
    private FileStorageService fileStorageService;

    @Value("${base.path}")
    String basePath;

    @Value("${storage.path}")
    String storagePath;

    @Value("${file.upload-dir}")
    String productFileUploadDir;


    public List<ImageModel> store(MultipartFile[] images){
        List<ImageModel> imageModels = new ArrayList<>();

        int i=0;
        for (MultipartFile file: images) {
            ImageModel tmp = this.store(file);
            if (tmp != null){
                imageModels.add(tmp);
            }
            if(i==0){
                createThumbnail(tmp, file);
            }
            i++;
        }
        return imageModels;
    }

    private void createThumbnail(ImageModel tmp, MultipartFile file) {
        if(file.getSize()>10000) {
        String thumbnailName = tmp.getName();
        thumbnailName = "thumbnail_" + thumbnailName;
        ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(basePath + storagePath)
                .path(thumbnailName)
                .toUriString();
    }else{
        try {
            Thumbnails.of(new File(productFileUploadDir+"/"+tmp.getName()))
                    .outputFormat("JPEG")
                    .size(80, 80)
                    .keepAspectRatio(true)
                    .outputQuality(1)
                    .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    public ImageModel store(MultipartFile image){
        String fileName = fileStorageService.storeFile(image);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(basePath+storagePath)
                .path(fileName)
                .toUriString();

        ImageModel imageModel = new ImageModel();
        imageModel.setUrl(fileDownloadUri);
        imageModel.setName(fileName);

        return imageModel;
    }
}
