package com.project.ychengspmall.model.entity.product;

import com.project.ychengspmall.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDetails extends BaseEntity {

	private Long productId;
	private String imageUrls;

}