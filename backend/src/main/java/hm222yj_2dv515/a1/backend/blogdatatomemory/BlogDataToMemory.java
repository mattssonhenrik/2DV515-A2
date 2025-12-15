package hm222yj_2dv515.a1.backend.blogdatatomemory;

import org.springframework.stereotype.Service;

/**
 * Holds blog names and their feature vectors in memory.
 * Index i in blogNames corresponds to row i in vectors.
 */
@Service
public class BlogDataToMemory {

    String[] blogNames;
    double[][] vectors;

    public BlogDataToMemory(String[] blogNames, double[][] vectors) {
        this.blogNames = blogNames;
        this.vectors = vectors;
    }

    public String[] getBlogNames() {
        return blogNames;
    }

    public double[][] getVectors() {
        return vectors;
    }
}
