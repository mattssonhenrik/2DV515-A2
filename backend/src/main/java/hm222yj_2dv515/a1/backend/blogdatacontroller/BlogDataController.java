package hm222yj_2dv515.a1.backend.blogdatacontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hm222yj_2dv515.a1.backend.clusterresponsedto.ClusterResponseDto;
import hm222yj_2dv515.a1.backend.kmeansclusteringservice.KMeansClusteringService;

/**
 * Exposes blog clustering results over HTTP.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class BlogDataController {

    KMeansClusteringService kMeansClusteringService;

    /**
     * Creates the controller with required dependencies.
     *
     * @param kMeansClusteringService service that runs K-means clustering
     */
    public BlogDataController(KMeansClusteringService kMeansClusteringService) {
        this.kMeansClusteringService = kMeansClusteringService;
    }

    /**
     * Runs K-means clustering and returns the result as JSON.
     *
     * @return clustering result for the blog dataset
     */
    @GetMapping("/clusters")
    public ClusterResponseDto getBlogClusters(@RequestParam(defaultValue = "5") int iterations) {

        return kMeansClusteringService.clusterBlogs(5, iterations);
    }
}