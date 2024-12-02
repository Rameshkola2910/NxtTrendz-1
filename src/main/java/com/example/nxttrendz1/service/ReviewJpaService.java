/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */
package com.example.nxttrendz1.service;

import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.repository.ProductJpaRepository;
import com.example.nxttrendz1.repository.ReviewJpaRepository;
import com.example.nxttrendz1.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class ReviewJpaService implements ReviewRepository {

	@Autowired
	private ReviewJpaRepository reviewJpaRepository;

	@Autowired
	private ProductJpaRepository productJpaRepository;

	@Override
	public ArrayList<Review> getReviews() {
		return (ArrayList<Review>) reviewJpaRepository.findAll();
	}

	@Override
	public Review getReviewById(int reviewId) {
		try {
			return reviewJpaRepository.findById(reviewId).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Review addReview(Review review) {
		Product product = review.getProduct();
		int produtctId = product.getProductId();
		try {
			Product newproduct1 = productJpaRepository.findById(produtctId).get();
			review.setProduct(newproduct1);
			reviewJpaRepository.save(review);
			return review;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "wrong productId");
		}
	}

	@Override
	public Review updateReview(int reviewId, Review review) {
		try {
			Review newreview = reviewJpaRepository.findById(reviewId).get();
			if (review.getReviewContent() != null) {
				newreview.setReviewContent(review.getReviewContent());
			}
			if (review.getRating() != 0) {
				newreview.setRating(review.getRating());
			}
			if (review.getProduct() != null) {
				Product product = review.getProduct();
				int produtctId = product.getProductId();
				Product productnew = productJpaRepository.findById(produtctId).get();
				newreview.setProduct(productnew);
			}
			reviewJpaRepository.save(newreview);
			return newreview;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteReview(int reviewId) {
		try {
			reviewJpaRepository.deleteById(reviewId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);
	}

	@Override
	public Product getProductReview(int reviewId) {
		try {
			Review review = reviewJpaRepository.findById(reviewId).get();
			return review.getProduct();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}