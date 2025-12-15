package hm222yj_2dv515.a1.backend.blogdatacontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hm222yj_2dv515.a1.backend.blogdatatomemory.BlogDataToMemory;
import hm222yj_2dv515.a1.backend.fileprocessor.FileProcessor;

/**
 * Exposes the raw blog dataset over HTTP.
 */
@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "http://localhost:5173")
public class BlogDataController {

    private final FileProcessor fileProcessor;

    /**
     * Creates the controller with required dependencies.
     *
     * @param fileProcessor reads the blog dataset from file
     */
    public BlogDataController(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    /**
     * Returns the dataset exactly as loaded by {@link FileProcessor}.
     *
     * @return blog names + vectors
     */
    @GetMapping("/blogdata")
    public BlogDataToMemory getBlogData() {
        return fileProcessor.loadData("blogdata.txt");
    }
}