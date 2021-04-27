package com.squirrel.uploadfilethymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    private static final String
            UPLOADED_FOLDER = "/Users/zhiyuanma/Code/Spring-learning/UploadFileThymeleaf/files/";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(
            @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it
            byte[] bytes = file.getBytes();
            // folder
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute(
                    "message", "You uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/multiple")
    public String uploadMultiple() {
        return "uploadMultiple";
    }

    @PostMapping("/uploadMultiple")
    public String multipleFileUpload(
            @RequestParam("file") MultipartFile[] files, RedirectAttributes redirectAttributes) {
        if (files == null || files.length == 0) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        for (MultipartFile file : files) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        redirectAttributes.addFlashAttribute("message", "You uploaded all");
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
