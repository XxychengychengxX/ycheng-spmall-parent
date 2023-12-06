package com.project.ychengspmall.model.entity.product;

import com.project.ychengspmall.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "商品规格实体类")
public class ProductSpec extends BaseEntity {

	@Schema(description = "规格名称")
	private String specName;   // 规格名称

	@Schema(description = "规格值")
	private String specValue;  // 规格值

}