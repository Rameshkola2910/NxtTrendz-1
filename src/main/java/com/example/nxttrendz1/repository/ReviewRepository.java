/*
 *
 * You can use the following import statements
 * 
 * import java.util.ArrayList;
 * 
 */

package com.example.nxttrendz1.repository;

import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.model.Review;
import java.util.*;

public interface ReviewRepository {

    ArrayList<Review> getReviews();

    Review getReviewById(int reviewId);

    Review addReview(Review review);

    Review updateReview(int reviewId, Review review);

    void deleteReview(int reviewId);

    Product getProductReview(int reviewId);
}